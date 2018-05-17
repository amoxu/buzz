package com.amoxu.util;

import com.alibaba.fastjson.JSON;
import com.hankcs.hanlp.HanLP;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;

public class HanlpUtil {


    public static String getKeyword(String string) {

        if (StringUtils.isBlank(string)) {
            return "";
        }

        int size = string.length() / 10;
        if (size > 5) {
            size = 5;
        }
        if (size == 0) {
            size = 1;
        }
        /*消除所有符号*/
        string = string.replaceAll("\\pP|\\pS|\\pC", " ");

        List<String> keywordList = HanLP.extractKeyword(string, size);

        string = StringUtils.join(keywordList.toArray(), "/");
        System.out.println(JSON.toJSONString(keywordList));
        if (string.length() > 64) {
            string = string.substring(0, 63);
        }


        return string;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("首次编译运行时，HanLP会自动构建词典缓存，请稍候……");
      //  HanLP.Config.enableDebug();         // 为了避免你等得无聊，开启调试模式说点什么:-)
        //System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！接下来请从其他Demo中体验HanLP丰富的功能￥"));
        // AhoCorasickDoubleArrayTrieSegment要求用户必须提供自己的词典路径
        /*AhoCorasickDoubleArrayTrieSegment segment = new AhoCorasickDoubleArrayTrieSegment()
                .loadDictionary(HanLP.Config.CustomDictionaryPath[0]);
        System.out.println(segment.seg("微观经济学继续教育循环经济"));
       */

        String content = "昨天聚会，你变漂亮了。";

        String testStr = "                            ";

        testStr = testStr.replaceAll( "\\pP|\\pS|\\pC"," ");
        System.out.println(testStr);

        System.out.println(getKeyword(testStr));




    }

}
class Config
{
    public static final String CWS_MODEL_FILE = HanLP.Config.PerceptronCWSModelPath;
    public static final String POS_MODEL_FILE = HanLP.Config.PerceptronPOSModelPath;
    public static final String NER_MODEL_FILE = HanLP.Config.PerceptronNERModelPath;
}
