package Scalors.Examination;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Parser
 *
 */
public class WebParser{
	
	private static OfferList offers = new OfferList();
	private static Set<String> urlSet = new HashSet<>();
	
    public static void main( String[] args ) throws IOException {
    	
    	
        String[] urls = {
//        		"https://www.aboutyou.de/p/diesel/sweatshirt-f-alby-fl-a-3766847",
//        		"https://www.aboutyou.de/p/boss-orange/kleid-dressie-3673308",
//        		"https://www.aboutyou.de/p/ugg/boots-mit-fell-fuetterung-mini-bailey-bow-ii-3485989",
//        		"https://www.aboutyou.de/p/ugg/boots-mit-fell-fuetterung-mini-bailey-bow-ii-3512097",
//        		"https://www.aboutyou.de/p/american-vintage/grobstrickjacke-mit-alpaka-boolder-3441256",
//        		"https://www.aboutyou.de/p/ugg/hausschuhe-birche-3689197",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/guertel-new-danny-2158002",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/sneaker-aus-leder-3669459",
//        		"https://www.aboutyou.de/p/lacoste/ziane-chunky-217-1-caw-wht-slipper-3624336",
//        		"https://www.aboutyou.de/p/juvia/hose-mit-leo-print-3709873",
//        		"https://www.aboutyou.de/p/american-vintage/pullover-vacaville-3699918",
//        		"https://www.aboutyou.de/p/lacoste/sneaker-carnaby-bl-1-3441211",
//        		"https://www.aboutyou.de/p/seafolly/badeanzug-block-party-3525459",
//        		"https://www.aboutyou.de/p/diesel/lederguertel-mit-breiten-loechern-3563517",
//        		"https://www.aboutyou.de/p/ugg/boots-mini-bailey-button-ii-3463633",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/stiefel-oxley-3415929",
//        		"https://www.aboutyou.de/p/samsoe-und-samsoe/bluse-mains-2269840",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/sneaker-venus-3688830",
//        		"https://www.aboutyou.de/p/hugo/sweatshirt-nicta-3789521",
//        		"https://www.aboutyou.de/p/calvin-klein/handyhuelle-marissa-3689727",
//        		"https://www.aboutyou.de/p/tigha/pullover-kylie-3701317",
//        		"https://www.aboutyou.de/p/hunter/gummistiefel-2131585",
//        		"https://www.aboutyou.de/p/ugg/snowboots-marice-3705219",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/shopper-poppy-3688121",
//        		"https://www.aboutyou.de/p/boss-orange/hose-sellie-aus-lederimitat-3705045",
//        		"https://www.aboutyou.de/p/samsoe-und-samsoe/overall-mit-spitze-willow-5687-1961394",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/guertel-new-danny-3570191",
//        		"https://www.aboutyou.de/p/zoe-karssen/janis-regular-fit-jeans-3651535",
//        		"https://www.aboutyou.de/p/boss-orange/shirt-tavana-3739637",
//        		"https://www.aboutyou.de/p/ugg/snowboots-classic-mini-3751817",
//        		"https://www.aboutyou.de/p/boss-orange/orange-j20-rienne-slimfit-jeans-3535831",
//        		"https://www.aboutyou.de/p/boss-orange/stoffhose-siledana-3535390",
//        		"https://www.aboutyou.de/p/calvin-klein/shopper-marissa-large-3710958",
//        		"https://www.aboutyou.de/p/boss-orange/t-shirt-vashirt-3628322",
//        		"https://www.aboutyou.de/p/american-vintage/pullover-woxilen-3699920",
//        		"https://www.aboutyou.de/p/zoe-karssen/sweatshirt-loose-fit-sweat-vers-la-lune-3749295",
//        		"https://www.aboutyou.de/p/tigha/lederjacke-im-bikerstil-nashua-3665303",
//        		"https://www.aboutyou.de/p/boss-orange/basicshirt-tafame-3536518",
//        		"https://www.aboutyou.de/p/samsoe-und-samsoe/spitzentop-aus-cupro-3593709",
//        		"https://www.aboutyou.de/p/tigha/oversized-pullover-rabea-3672459",
//        		"https://www.aboutyou.de/p/diesel/skinzee-low-zip-jeans-slimfit-856g-3757528",
//        		"https://www.aboutyou.de/p/set/stretch-lederleggings-3414167",
//        		"https://www.aboutyou.de/p/boss-orange/jeans-j21-austin-3679656",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/guertel-new-danny-3565383",
//        		"https://www.aboutyou.de/p/american-vintage/grobstrickjacke-mit-alpaka-boolder-3715184",
//        		"https://www.aboutyou.de/p/hugo/sweatshirt-nicci-3788252",
//        		"https://www.aboutyou.de/p/calvin-klein-jeans/t-shirt-tanya-18-3685362",
//        		"https://www.aboutyou.de/p/tigha/lederhose-nori-3562176",
//        		"https://www.aboutyou.de/p/ugg/boots-mit-fuetterung-3681986",
//        		"https://www.aboutyou.de/p/joop/shopper-cortina-lara-3713886",
//        		"https://www.aboutyou.de/p/juvia/hose-im-jogging-stil-3669738",
//        		"https://www.aboutyou.de/p/boss-orange/feinstrickpullover-iddyenna-3699131",
//        		"https://www.aboutyou.de/p/ugg/boots-classic-short-ii-3450819",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/schmaler-lederguertel-seasonal-3785433",
//        		"https://www.aboutyou.de/p/diesel/t-shirt-mit-print-t-sully-long-i-3644841",
//        		"https://www.aboutyou.de/p/boss-orange/skinny-jeans-orange-j20-rienne-3679659",
//        		"https://www.aboutyou.de/p/ugg/boots-mini-bailey-button-ii-3456507",
//        		"https://www.aboutyou.de/p/boss-orange/t-shirt-tiplisse-3693461",
//        		"https://www.aboutyou.de/p/7-for-all-mankind/skinny-denim-3448824",
//        		"https://www.aboutyou.de/p/calvin-klein-jeans/pullover-harper-true-logo-3667998",
//        		"https://www.aboutyou.de/p/ugg/hausschuh-birche-3470175",
//        		"https://www.aboutyou.de/p/ugg/boots-mit-fell-fuetterung-mini-bailey-bow-ii-3512103",
//        		"https://www.aboutyou.de/p/boss-orange/sweater-taloboss-3789558",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/sneaker-sady-3669451",
//        		"https://www.aboutyou.de/p/american-vintage/grobstrickjacke-mit-alpaka-boolder-3684406",
//        		"https://www.aboutyou.de/p/samsoe-und-samsoe/muetze-aus-alpaca-merino-mix-3716617",
//        		"https://www.aboutyou.de/p/joop/handtasche-cortina-thoosa-3661957",
//        		"https://www.aboutyou.de/p/tigha/tunika-reyes-3717309",
//        		"https://www.aboutyou.de/p/lacoste/sneaker-carnaby-evo-3610375",
//        		"https://www.aboutyou.de/p/calvin-klein/umhaengetasche-marissa-3706086",
//        		"https://www.aboutyou.de/p/7-for-all-mankind/the-skinny-skinny-jeans-3498029",
//        		"https://www.aboutyou.de/p/set/spitzentop-mit-spaghettitraegern-3561180",
//        		"https://www.aboutyou.de/p/ugg/boots-classic-mini-ii-3480317",
//        		"https://www.aboutyou.de/p/boss-orange/tuch-nafame-3513918",
//        		"https://www.aboutyou.de/p/diesel/shirt-t-gertrude-h-3757525",
//        		"https://www.aboutyou.de/p/diesel/oversized-shirt-t-supa-3677360",
//        		"https://www.aboutyou.de/p/ugg/boots-bailey-bow-ii-3472002",
//        		"https://www.aboutyou.de/p/set/stretch-lederleggings-3673569",
//        		"https://www.aboutyou.de/p/joop/cortina-dia-umhaengetasche-24-cm-3596351",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/stiefel-aline-3682243",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/shirt-chiara-3586001",
//        		"https://www.aboutyou.de/p/calvin-klein-jeans/shirt-shrunken-2198651",
//        		"https://www.aboutyou.de/p/boss-orange/kleid-amodisa-3659533",
//        		"https://www.aboutyou.de/p/hugo/bluse-etrina-3790376",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/stiefelette-mit-zierschnalle-3709040",
//        		"https://www.aboutyou.de/p/boss-orange/tuch-nafame-3626594",
//        		"https://www.aboutyou.de/p/calvin-klein-jeans/shirt-shrunken-3413132",
//        		"https://www.aboutyou.de/p/ugg/boots-classic-mini-ii-3472005",
//        		"https://www.aboutyou.de/p/ugg/boots-classic-mini-ii-3472006",
//        		"https://www.aboutyou.de/p/samsoe-und-samsoe/shirt-nana-3471403",
//        		"https://www.aboutyou.de/p/schott-nyc/bomberjacket-3514181",
//        		"https://www.aboutyou.de/p/tigha/lederjacke-alexandra-3421122",
//        		"https://www.aboutyou.de/p/joop/shopper-cortina-lara-3693473",
//        		"https://www.aboutyou.de/p/tommy-hilfiger/stiefeletten-tessa-3700845",
//        		"https://www.aboutyou.de/p/boss-orange/t-shirt-tashirti-3703944",
//        		"https://www.aboutyou.de/p/diesel/casual-t-shirt-3766859",
//        		"https://www.aboutyou.de/p/american-vintage/cardigan-vacaville-3699932",
//        		"https://www.aboutyou.de/p/boss-orange/shirt-tacrew-3623822",
//        		"https://www.aboutyou.de/p/ugg/stiefelette-milla-3746436",

        		
        		
        		
        		
//        		"https://www.aboutyou.de/p/calvin-klein-jeans/t-shirt-mit-logo-print-3728834",
//        		"https://www.aboutyou.de/p/nike/trainingsschuh-metcon-3-3548844",
//        		"https://www.aboutyou.de/p/new-era/9fifty-los-angeles-dodgers-cap-3618671",
//        		"https://www.aboutyou.de/p/american-vintage/grobstrickjacke-mit-alpaka-boolder-3684406",
        };
        
        ProductLinkFinder plf = new ProductLinkFinder("https://www.aboutyou.de");
        plf.gatherLinks();
        
        urlSet.addAll(plf.getOfferLinkSet());
  //      urlSet.addAll(Arrays.asList(urls));
        
        int count = 0;
        
        for (String url : urlSet) {		
		
        Document doc = Jsoup.connect(url).get();
        Element body = doc.body(); 
        Element scriptElement = body.select("script").first();
        Product prod = new Product(scriptElement);
 System.out.println(++count);
        offers.add(prod);
        }
        
       
        
        
       
     //   System.out.println(stringWithProduct);
     //   System.out.println("products size: " + scriptElement.data());

  //      System.out.println(new ProductStringParser().getProductString(stringWithProduct));
        
        OfferXMLWritter writter = new OfferXMLWritter(new File("result.xml"));
		writter.write(offers);
      
		System.out.println("done");
    }
}
