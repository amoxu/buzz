package com.amoxu.util.Datx;

import com.alibaba.fastjson.JSON;
import com.amoxu.entity.Coordinate;
import com.amoxu.entity.CoordinateExample;
import com.amoxu.mapper.CoordinateMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
public class IpUtil {



    private static final Pattern IPV4_PATTERN = Pattern.compile("^(([1-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){1}(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){2}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");

    public static boolean isIPv4Address(String input) {
        return IPV4_PATTERN.matcher(input).matches();
    }

    public static long bytesToLong(byte a, byte b, byte c, byte d) {
        return int2long((((a & 0xff) << 24) | ((b & 0xff) << 16) | ((c & 0xff) << 8) | (d & 0xff)));
    }

    private static int str2Ip(String ip)  {
        String[] ss = ip.split("\\.");
        int a, b, c, d;
        a = Integer.parseInt(ss[0]);
        b = Integer.parseInt(ss[1]);
        c = Integer.parseInt(ss[2]);
        d = Integer.parseInt(ss[3]);
        return (a << 24) | (b << 16) | (c << 8) | d;
    }

    public static int str2Ip2(String ip)  {
        try {
            byte[] bytes = java.net.InetAddress.getByName(ip).getAddress();

            return ((bytes[0] & 0xFF) << 24) |
                    ((bytes[1] & 0xFF) << 16) |
                    ((bytes[2] & 0xFF) << 8) |
                    bytes[3];
        } catch (java.net.UnknownHostException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public static long ip2long(String ip)  {
        return int2long(str2Ip(ip));
    }

    private static long int2long(int i) {
        long l = i & 0x7fffffffL;
        if (i < 0) {
            l |= 0x080000000L;
        }
        return l;
    }


    private static final String PROPERTIES_PATH = "ipip/ipip.datx";


    public Coordinate getLocation(String ip, SqlSessionFactory factory) {

        CoordinateExample coordinateExample = new CoordinateExample();
        CoordinateExample.Criteria criteria = coordinateExample.createCriteria();
        coordinateExample.setLimit(1);

        StringBuilder loc = new StringBuilder();
        List<Coordinate> coordinates = Collections.emptyList();

        try (SqlSession session = factory.openSession()) {
            CoordinateMapper mapper = session.getMapper(CoordinateMapper.class);


            String path = IpUtil.class.getClassLoader().getResource(PROPERTIES_PATH).getPath();
            path = path.substring(1, path.length());

            City city = new City(path); // 城市库
            String[] strings = city.find(ip);//find("61.190.213.226");
            List<String> list = new ArrayList<>();
            for (int i = strings.length; i >= 0; i--) {
                loc = new StringBuilder();

                for (int j = 0; j < i; j++) {
                    System.out.println("idx :" + strings[j]);
                    if (StringUtils.isNotBlank(strings[j])) {
                        list.add(strings[j]);
                        loc.append(strings[j]).append("%");
                    }

                }
                criteria.andMergerNameLike(loc.toString());

                coordinates = mapper.selectByExample(coordinateExample);
                if (coordinates != null && coordinates.size() > 0) {
                    break;
                }
                loc = null;
            }

            if (coordinates == null || coordinates.size() == 0) {
                return null;
            }
            //System.out.println(JSON.toJSONString(coordinates));
            return coordinates.get(0);
            //完成数据库的插入
        } catch (IOException | IPv4FormatException ioex) {
            ioex.printStackTrace();
        }


        return null;
    }



    public static void main(String[] args) throws IOException {
        CoordinateExample coordinateExample = new CoordinateExample();
        CoordinateExample.Criteria criteria = coordinateExample.createCriteria();


        String loc = null;

        try {


            SqlSession session = null;

            assert session != null;
            CoordinateMapper mapper = session.getMapper(CoordinateMapper.class);




        String path = IpUtil.class.getClassLoader().getResource(PROPERTIES_PATH).getPath();
        path = path.substring(1, path.length());

            City city = new City(path); // 城市库
            String[] strings = city.find("61.190.213.226");
            List<String> list = new ArrayList<>();
            for (String s : strings) {
                System.out.println("idx :" + s);
                if (StringUtils.isNotBlank(s)) {
                    list.add(s);
                    loc += "%" + s;
                }


            }
            criteria.andMergerNameLike(loc);
            List<Coordinate> coordinates = mapper.selectByExample(coordinateExample);

            System.out.println(JSON.toJSONString(coordinates));

            //完成数据库的插入
            session.commit();
            session.close();
        } catch (IOException | IPv4FormatException ioex) {
            ioex.printStackTrace();
        }



    }



}