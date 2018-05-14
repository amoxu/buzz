package com.amoxu.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;

public class HanlpUtil {
    public static void main(String[] args) throws IOException {
        System.out.println("首次编译运行时，HanLP会自动构建词典缓存，请稍候……");
        HanLP.Config.enableDebug();         // 为了避免你等得无聊，开启调试模式说点什么:-)
        //System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！接下来请从其他Demo中体验HanLP丰富的功能~"));
        // AhoCorasickDoubleArrayTrieSegment要求用户必须提供自己的词典路径
        /*AhoCorasickDoubleArrayTrieSegment segment = new AhoCorasickDoubleArrayTrieSegment()
                .loadDictionary(HanLP.Config.CustomDictionaryPath[0]);
        System.out.println(segment.seg("微观经济学继续教育循环经济"));
       */
        String content = "跟Alan Walker的Fade有异曲同工之妙。";
        Segment segment = HanLP.newSegment().enableJapaneseNameRecognize(true);
        List<String> keywordList = HanLP.extractKeyword(content, 5);
        System.out.println(keywordList);
        CustomDictionary.load(HanLP.Config.CustomDictionaryPath[2],  Nature.a, new TreeMap<String, CoreDictionary.Attribute>(), new LinkedHashSet<Nature>());
        List<Term> termList = NLPTokenizer.segment("跟Alan Walker的Fade有异曲同工之妙。");
        System.out.println(termList);
        /*
        PerceptronSegmenter segmenter = new PerceptronSegmenter(HanLP.Config.CWS_MODEL_FILE);
        System.out.println(segmenter.segment("下雨天地面积水"));*/
    }
}
