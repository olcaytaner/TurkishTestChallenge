package Similarity;

import Corpus.Sentence;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class run {
    public static void main(String[] args) throws IOException, JAXBException {

        Sentence cbnzr1=new Sentence("Bugün yemek yedim");
        Sentence cbnzr2=new Sentence("Bugün yemek yiyebilirdim");
        Sentence azbnr1=new Sentence("Hava sıcakken dışarıda top oynuyabilirsin.");
        Sentence azbnr2=new Sentence("Sıcak havada top oynayayım");
        Sentence hcbnmz1=new Sentence("Yemekler çok güzel olmuş");
        Sentence hcbnmz2=new Sentence("Zil çalınca kapıya doğru yöneldi.");

        Sentence chocie1=new Sentence("Yazarlar da tıpkı diğer insanlar gibi duygularını, düşüncelerini çevrelerinden edinirler.");
        Sentence chocie2=new Sentence("Onların ayırt edici özelliği, bu edinimlerini yazıya dökerek kalıcılaştırmalarıdır.");
        Sentence chocie3=new Sentence("Yazarlar bizim yaşayıp unutuverdiğimiz ve soluk birer anıya dönüştürdüğümüz yaşam parçacıklarını kitaplarında ölümsüzleştirirler.");
        Sentence chocie4=new Sentence("Böylece bu anılar hiçbir zaman solup yok olmaz.");
        Sentence chocie5=new Sentence("Gelecek kuşaklara ışık tutan birer meşaleye dönüşür.");

        QgramSimilarityIOld sim=new QgramSimilarityIOld(3);
        float scoreA=sim.similarityScore(chocie1,chocie2);
        float scoreB=sim.similarityScore(chocie1,chocie3);
        float scoreC=sim.similarityScore(chocie1,chocie4);
        float scoreD=sim.similarityScore(chocie1,chocie5);
        float scoreE=sim.similarityScore(chocie2,chocie3);
        float scoreF=sim.similarityScore(chocie2,chocie4);
        float scoreG=sim.similarityScore(chocie2,chocie5);
        float scoreH=sim.similarityScore(chocie3,chocie4);
        float scoreI=sim.similarityScore(chocie3,chocie5);
        float scoreJ=sim.similarityScore(chocie4,chocie5);
        //float score=sim.similarityScore(cbnzr1,cbnzr2);
        //float score1=sim.similarityScore(azbnr1,azbnr2);
        //float score2=sim.similarityScore(hcbnmz1,hcbnmz2);

        OverlapCoefficient coeff=new OverlapCoefficient();
        float simA=coeff.similarityScore(chocie1,chocie2);
        float simB=coeff.similarityScore(chocie1,chocie3);
        float simC=coeff.similarityScore(chocie1,chocie4);
        float simD=coeff.similarityScore(chocie1,chocie5);
        float simE=coeff.similarityScore(chocie2,chocie3);
        float simF=coeff.similarityScore(chocie2,chocie4);
        float simG=coeff.similarityScore(chocie2,chocie5);
        float simH=coeff.similarityScore(chocie3,chocie4);
        float simI=coeff.similarityScore(chocie3,chocie5);
        float simJ=coeff.similarityScore(chocie4,chocie5);
        //float score3=coeff.similarityScore(cbnzr1,cbnzr2);
        //float score4=coeff.similarityScore(azbnr1,azbnr2);
        //float score5=coeff.similarityScore(hcbnmz1,hcbnmz2);

        BlockDistance distnc=new BlockDistance();//ters orantılı
        float distncA=distnc.similarityScore(chocie1,chocie2);
        float distncB=distnc.similarityScore(chocie1,chocie3);
        float distncC=distnc.similarityScore(chocie1,chocie4);
        float distncD=distnc.similarityScore(chocie1,chocie5);
        float distncE=distnc.similarityScore(chocie2,chocie3);
        float distncF=distnc.similarityScore(chocie2,chocie4);
        float distncG=distnc.similarityScore(chocie2,chocie5);
        float distncH=distnc.similarityScore(chocie3,chocie4);
        float distncI=distnc.similarityScore(chocie3,chocie5);
        float distncJ=distnc.similarityScore(chocie4,chocie5);
        //float score6=distnc.similarityScore(cbnzr1,cbnzr2);
        //float score7=distnc.similarityScore(azbnr1,azbnr2);
        //float score8=distnc.similarityScore(hcbnmz1,hcbnmz2);

        JaccardSimilarityIOld jaccard=new JaccardSimilarityIOld();
        float jaccardA=jaccard.similarityScore(chocie1,chocie2);
        float jaccardB=jaccard.similarityScore(chocie1,chocie3);
        float jaccardC=jaccard.similarityScore(chocie1,chocie4);
        float jaccardD=jaccard.similarityScore(chocie1,chocie5);
        float jaccardE=jaccard.similarityScore(chocie2,chocie3);
        float jaccardF=jaccard.similarityScore(chocie2,chocie4);
        float jaccardG=jaccard.similarityScore(chocie2,chocie5);
        float jaccardH=jaccard.similarityScore(chocie3,chocie4);
        float jaccardI=jaccard.similarityScore(chocie3,chocie5);
        float jaccardJ=jaccard.similarityScore(chocie4,chocie5);
        //float score9=jaccard.similarityScore(cbnzr1,cbnzr2);
        //float score10=jaccard.similarityScore(azbnr1,azbnr2);
        //float score11=jaccard.similarityScore(hcbnmz1,hcbnmz2);

        LevenshteinDistance lvstDstnc=new LevenshteinDistance();
        float lvstDstncA=lvstDstnc.similarityScore(chocie1,chocie2);
        float lvstDstncB=lvstDstnc.similarityScore(chocie1,chocie3);
        float lvstDstncC=lvstDstnc.similarityScore(chocie1,chocie4);
        float lvstDstncD=lvstDstnc.similarityScore(chocie1,chocie5);
        float lvstDstncE=lvstDstnc.similarityScore(chocie2,chocie3);
        float lvstDstncF=lvstDstnc.similarityScore(chocie2,chocie4);
        float lvstDstncG=lvstDstnc.similarityScore(chocie2,chocie5);
        float lvstDstncH=lvstDstnc.similarityScore(chocie3,chocie4);
        float lvstDstncI=lvstDstnc.similarityScore(chocie3,chocie5);
        float lvstDstncJ=lvstDstnc.similarityScore(chocie4,chocie5);
        //float score12=lvstDstnc.similarityScore(cbnzr1,cbnzr2);
        //float score13=lvstDstnc.similarityScore(azbnr1,azbnr2);
        //float score14=lvstDstnc.similarityScore(hcbnmz1,hcbnmz2);
        if(lvstDstncJ==0.0f){
            System.out.print("S2,S3");
        }
    }
}
