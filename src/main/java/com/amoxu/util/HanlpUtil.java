package com.amoxu.util;

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

        String[] testStr = {
                "人，其實不必偽裝自己。要活得如幼童般，真心感受及表達自己最真的感覺。就算遺失了感覺，也要耐心把它找回來。"

                , "许镜清不愧大师名号，天竺少女配乐电音，少数民族乐器，异域风情三者合一，而且没有任何维和感，超前的音乐思维，可以说我在听到周杰伦之前，没遇到一个音乐家是这么有才华，或者说这么敢外露才华的！"

                , "把这首歌和中孝介的《各自远洋》一起放竟然毫无维和感[惊恐]"

                , "這是我潑的第六張專輯 一改以往偏techno的曲風 轉向了時下流行的house和各種edm風格 給人煥然一新的感覺 編曲輕盈雅致 也不乏活潑 從pv到包裝再到整張專輯本身都能感覺到環球和中田女士的用心"

                , "日本知名网站niconico是中国B站的原型，双笙在上面发布的《采茶纪》、《月出》等四首古风歌曲，不到两周时间里被点击了25000多次，平均每天有2000个日本网友在niconico上面听双笙唱歌。日本年轻人形容她是“最具大唐感的中国女声”，“听完之后前所未有地想学汉语”。"

                , "有一种看透世事的沧桑和平和感！另一种味道，以男人的视角看那场凄美的爱情"

                , "这俩人的MV给我一种老夫老妻的祥和感x"

                , "不知为什么这首歌总会给我一种无限对高中的怀恋，即使我刚刚离开，我觉得一首歌能让人有这样的情感共鸣就够了，还有节目中的每位明星都给人平和感，都很不错????"

                , "仿佛看到了AR小组在严冬中冒着暴雪迷茫在铁血的管辖区域。危机四伏，四面埋伏。喜欢a参杂的呼吸声和给人带来的森严感，更喜欢b给人带来的紧张感与刺激感以及暴雪作战的空灵。"
                , "看着一群妹子在冒着风雪挺进敌后我们这些大老爷们还整天躺在暖和的床上抱怨着怎么死也抽不到五星[流泪]"

                , "卧槽？就问问oxlxs和林宥嘉哪个有名？没有他们翻唱照样很多人听过，我也很喜欢oxlxs但是你这样说是真的很招黑也很败路人感的[口罩][口罩]"

        };

        for (String s : testStr) {
            System.out.println(getKeyword("这四个是男的！这四个是男的！这四个是男的！这四个是男的！这四个是男的！这是个是男的！"));
        }
    }

}
class Config
{
    public static final String CWS_MODEL_FILE = HanLP.Config.PerceptronCWSModelPath;
    public static final String POS_MODEL_FILE = HanLP.Config.PerceptronPOSModelPath;
    public static final String NER_MODEL_FILE = HanLP.Config.PerceptronNERModelPath;
}
