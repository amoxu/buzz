package com.amoxu.util;

import org.python.util.PythonInterpreter;

import java.util.Properties;

public class PythonUtil {
    public static void main(String[] args) {
        Properties props = new Properties();
//      props.put("python.home","path to the Lib folder");
        props.put("python.console.encoding", "UTF-8"); // Used to prevent: console: Failed to install '': java.nio.charset.UnsupportedCharsetException: cp0.
        props.put("python.security.respectJavaAccessibility", "false"); //don't respect java accessibility, so that we can access protected members on subclasses
        props.put("python.import.site","false");
        Properties preprops = System.getProperties();

        PythonInterpreter.initialize(preprops, props, new String[0]);
        PythonInterpreter interp = new PythonInterpreter();

        //System.setProperty("python.home", "F:/Maven/LocalWarehouse/org/python");
        //System.setProperty("python.home", "F:/JWP/SentimentPolarityAnalysis/spa");


        interp.exec("import sys");
        interp.exec("sys.path.append('F:/Maven/LocalWarehouse/org/python/jython-standalone/2.7.0')");//jython自己的
        //interp.exec("sys.path.append('D:/Program Files (x86)/jython2.7.0/Lib/site-packages')");//jython自己的
        interp.exec("sys.path.append('F:/JWP/SentimentPolarityAnalysis/spa/*.py')");//我们自己写的
        interp.exec("sys.path.append('F:/JWP/SentimentPolarityAnalysis/spa/test.py')");//我们自己写的
        interp.execfile("F:/JWP/SentimentPolarityAnalysis/spa/test.py");

/*        PyFunction pyFunction = interp.get("mian", PyFunction.class); // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyObject pyObject = pyFunction.__call__(); // 调用函数*/

/*        System.out.println(pyObject);*/
        interp.getSystemState();

       /* // Python程序路径
        String python = "F:/JWP/SentimentPolarityAnalysis/spa/test.py";
        // Python实例对象名
        //String pyObjName = "pyController";
        // Python类名
        //String pyClazzName = "FruitController";


        PythonInterpreter interpreter = new PythonInterpreter();
        // 如果在Python程序中引用了第三方库,需要将这些被引用的第三方库所在路径添加到系统环境变量中
        // 否则,在执行Python程序时将会报错: ImportError: No module named xxx
        PySystemState sys = interpreter.getSystemState();
        sys.path.add("F:/JWP/SentimentPolarityAnalysis/spa");

        // 加载Python程序
        interpreter.execfile(python);*/
        // 实例 Python对象
        //interpreter.exec(pyObjName + "=" + pyClazzName + "()");

        // 1.在Java中获取Python对象,并将Python对象转换为Java对象
        // 为什么能够转换? 因为Python类实现了Java接口,通过转换后的Java对象只能调用接口中定义的方法



        // 2.在Java直接通过Python对象调用其方法
        // 既可以调用实现的Java接口方法,也可以调用Python类自定义的方法
        //PyObject pyObject = interpreter.get(pyObjName);
       /* pyObject.invoke("controllFruit", Py.java2py(apple));
        pyObject.invoke("controllFruit", Py.java2py(orange));
        pyObject.invoke("printFruit", Py.java2py(apple));
        pyObject.invoke("printFruit", Py.java2py(orange));

        // 3.在Java中获取Python类进行实例化对象: 没有事先创建 Python对象
        PyObject pyClass = interpreter.get("FruitController");
        PyObject pyObj = pyClass.__call__();
        pyObj.invoke("controllFruit", Py.java2py(apple));
        pyObj.invoke("controllFruit", Py.java2py(orange));

        PyObject power = pyObj.invoke("power", new PyObject[] {Py.newInteger(2), Py.newInteger(3)});
        if(power != null) {
            double p = Py.py2double(power);
            System.out.println(p);
        }
*/
/*        interpreter.cleanup();
        interpreter.close();*/
    }

}
