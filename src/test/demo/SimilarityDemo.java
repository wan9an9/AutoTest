package test.demo;

import java.util.List;

import test.chnlp.AHANLP;

public class SimilarityDemo {

    public static void main(String[] args) {
        
        String s1 = "苏州有多条公路正在施工，造成局部地区汽车行驶非常缓慢。";
        String s2 = "苏州最近有多条公路在施工，导致部分地区交通拥堵，汽车难以通行。";
        String s3 = "苏州是一座美丽的城市，四季分明，雨量充沛。";
        
        System.out.println("or");
        System.out.println("s1 | s1 : " + AHANLP.sentenceSimilarity("NLP", s1, s1));
        System.out.println("s1 | s2 : " + AHANLP.sentenceSimilarity("NLP", s1, s2));
        System.out.println("s1 | s3 : " + AHANLP.sentenceSimilarity("NLP", s1, s3));
        
    }
}
