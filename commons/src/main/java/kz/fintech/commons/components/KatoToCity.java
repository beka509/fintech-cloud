package kz.fintech.commons.components;

import java.util.HashMap;

public class KatoToCity {

    public static final HashMap<String, String> egovKatoToColvirDepIdForSks = new HashMap<>();
    public static final HashMap<String, String> egovKatoToColvirDepCode = new HashMap<>();

    private static final String NUR_SULTAN_CITY = "71";
    private static final String ALMATY_CITY = "75";
    private static final String SHYMKENT_CITY = "79";
    private static final String KOKSHETAU_CITY = "111010000";
    private static final String ABAY_REGION = "10";
    private static final String AKMOLA_REGION = "11";
    private static final String AKTOBE_REGION = "15";
    private static final String ALMATY_REGION = "19";
    private static final String ATYRAU_REGION = "23";
    private static final String EAST_KAZAKHSTAN_REGION = "63";
    private static final String ZHAMBYL_REGION = "31";
    private static final String WEST_KAZAKHSTAN_REGION = "27";
    private static final String KARAGANDY_REGION = "35";
    private static final String ULYTAU_REGION = "62";
    private static final String KOSTANAY_REGION = "39";
    private static final String KYZYLORDA_REGION = "43";
    private static final String MANGYSTAU_REGION = "47";
    private static final String PAVLODAR_REGION = "55";
    private static final String NORTH_KAZAKHSTAN_REGION = "59";
    private static final String TURKESTAN_REGION = "61";
    private static final String TURKESTAN_REGION_2 = "51";
    private static final String JETYSU_REGION = "33";
    private static final String AKTAU_REGION = "16";

    private static final String KOKSHETAU_BRANCH = "7";
    private static final String SHYMKENT_BRANCH = "4";
    private static final String ALMATY_BRANCH = "26";
    private static final String OSKEMEN_BRANCH = "27";
    private static final String PAVLODAR_BRANCH = "28";
    private static final String AKTOBE_BRANCH = "29";
    private static final String NUR_SULTAN_BRANCH = "34";
    private static final String KARAGANDY_BRANCH = "35";
    private static final String KOSTANAY_BRANCH = "8";
    private static final String AKTAU_BRANCH = "16";
    private static final String TARAZ_BRANCH = "5";
    private static final String ATYRAU_BRANCH = "15";

    private static final String KOKSHETAU_CODE = "800000";
    private static final String SHYMKENT_CODE = "700000";
    private static final String ALMATY_CODE = "200000";
    private static final String OSKEMEN_CODE = "900000";
    private static final String PAVLODAR_CODE = "500000";
    private static final String AKTOBE_CODE = "400000";
    private static final String NUR_SULTAN_CODE = "300000";
    private static final String KARAGANDY_CODE = "600000";
    private static final String KOSTANAY_CODE = "810000";
    private static final String AKTAU_CODE = "410000";
    private static final String TARAZ_CODE = "710000";
    private static final String ATYRAU_CODE = "420000";

    static {
        egovKatoToColvirDepIdForSks.put(NUR_SULTAN_CITY, NUR_SULTAN_BRANCH);
        egovKatoToColvirDepIdForSks.put(ALMATY_CITY, ALMATY_BRANCH);
        egovKatoToColvirDepIdForSks.put(SHYMKENT_CITY, SHYMKENT_BRANCH);
        egovKatoToColvirDepIdForSks.put(AKMOLA_REGION, NUR_SULTAN_BRANCH);
        egovKatoToColvirDepIdForSks.put(AKTOBE_REGION, AKTOBE_BRANCH);
        egovKatoToColvirDepIdForSks.put(ALMATY_REGION, ALMATY_BRANCH);
        egovKatoToColvirDepIdForSks.put(JETYSU_REGION, ALMATY_BRANCH);
        egovKatoToColvirDepIdForSks.put(EAST_KAZAKHSTAN_REGION, OSKEMEN_BRANCH);
        egovKatoToColvirDepIdForSks.put(ABAY_REGION, OSKEMEN_BRANCH);
        egovKatoToColvirDepIdForSks.put(WEST_KAZAKHSTAN_REGION, AKTOBE_BRANCH);
        egovKatoToColvirDepIdForSks.put(KARAGANDY_REGION, KARAGANDY_BRANCH);
        egovKatoToColvirDepIdForSks.put(ULYTAU_REGION, KARAGANDY_BRANCH);
        egovKatoToColvirDepIdForSks.put(KOSTANAY_REGION, KOSTANAY_BRANCH);
        egovKatoToColvirDepIdForSks.put(KYZYLORDA_REGION, SHYMKENT_BRANCH);
        egovKatoToColvirDepIdForSks.put(MANGYSTAU_REGION, AKTOBE_BRANCH);
        egovKatoToColvirDepIdForSks.put(PAVLODAR_REGION, PAVLODAR_BRANCH);
        egovKatoToColvirDepIdForSks.put(NORTH_KAZAKHSTAN_REGION, KOKSHETAU_BRANCH);
        egovKatoToColvirDepIdForSks.put(TURKESTAN_REGION, SHYMKENT_BRANCH);
        egovKatoToColvirDepIdForSks.put(TURKESTAN_REGION_2, SHYMKENT_BRANCH);
        egovKatoToColvirDepIdForSks.put(KOKSHETAU_CITY, KOKSHETAU_BRANCH);
        egovKatoToColvirDepIdForSks.put(AKTAU_REGION, AKTAU_BRANCH);
        egovKatoToColvirDepIdForSks.put(ZHAMBYL_REGION, TARAZ_BRANCH);
        egovKatoToColvirDepIdForSks.put(ATYRAU_REGION, ATYRAU_BRANCH);
    }

    static {
        egovKatoToColvirDepCode.put(NUR_SULTAN_CITY, NUR_SULTAN_CODE);
        egovKatoToColvirDepCode.put(ALMATY_CITY, ALMATY_CODE);
        egovKatoToColvirDepCode.put(ALMATY_REGION, ALMATY_CODE);
        egovKatoToColvirDepCode.put(JETYSU_REGION, ALMATY_CODE);
        egovKatoToColvirDepCode.put(SHYMKENT_CITY, SHYMKENT_CODE);
        egovKatoToColvirDepCode.put(AKMOLA_REGION, NUR_SULTAN_CODE);
        egovKatoToColvirDepCode.put(AKTOBE_REGION, AKTOBE_CODE);
        egovKatoToColvirDepCode.put(EAST_KAZAKHSTAN_REGION, OSKEMEN_CODE);
        egovKatoToColvirDepCode.put(ABAY_REGION, OSKEMEN_CODE);
        egovKatoToColvirDepCode.put(WEST_KAZAKHSTAN_REGION, AKTOBE_CODE);
        egovKatoToColvirDepCode.put(KARAGANDY_REGION, KARAGANDY_CODE);
        egovKatoToColvirDepCode.put(ULYTAU_REGION, KARAGANDY_CODE);
        egovKatoToColvirDepCode.put(KYZYLORDA_REGION, SHYMKENT_CODE);
        egovKatoToColvirDepCode.put(MANGYSTAU_REGION, AKTOBE_CODE);
        egovKatoToColvirDepCode.put(PAVLODAR_REGION, PAVLODAR_CODE);
        egovKatoToColvirDepCode.put(NORTH_KAZAKHSTAN_REGION, KOKSHETAU_CODE);
        egovKatoToColvirDepCode.put(TURKESTAN_REGION, SHYMKENT_CODE);
        egovKatoToColvirDepCode.put(TURKESTAN_REGION_2, SHYMKENT_CODE);
        egovKatoToColvirDepCode.put(KOKSHETAU_CITY, KOKSHETAU_CODE);
        egovKatoToColvirDepCode.put(KOSTANAY_REGION, KOSTANAY_CODE);
        egovKatoToColvirDepCode.put(AKTAU_REGION, AKTAU_CODE);
        egovKatoToColvirDepCode.put(ZHAMBYL_REGION, TARAZ_CODE);
        egovKatoToColvirDepCode.put(ATYRAU_REGION, ATYRAU_CODE);
    }
}
