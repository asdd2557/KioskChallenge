package org.example.resource;

public enum MenuEnum {
    //=====================================버거 메뉴=================================================
    SHACKBURGER("토마토, 양상추, 쉑소스가 토핑된 치즈버거", "Burger", 6.9, 33),
    SMOKESHACK("베이커, 체리 페퍼에 쉑소스가 토핑된 치즈버거", "Burger", 8.9, 33),
    CHEESEBURGER("포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", "Burger", 6.9, 33),
    HAMBURGER("a포테이토 번과 비프패티, 치즈가 토핑된 치즈버거", "Burger", 6.9, 33),

    //=====================================디저트 메뉴=================================================
    CHOCOLATESHAKE("달콤하고 진한 초콜릿 맛이 가득한 부드러운 쉐이크", "Desserts", 5.5, 33),
    VANILLAICECREAM("천연 바닐라 향과 부드러운 크림이 어우러진 아이스크림", "Desserts", 4.9, 33),
    CARAMELPUDDING("녹아내리는 카라멜 소스와 쫀득한 푸딩의 완벽한 조합", "Desserts", 5.2, 33),
    CHEESECAKE("풍부한 치즈 향과 부드러운 식감이 매력적인 치즈케이크", "Desserts", 6.0, 33),

    //=====================================드링크 메뉴=================================================
    COLA("톡쏘는 청량감이 매력적인 클래식 콜라!", "Drink", 2.9, 33),
    SPRITE("상쾌한 레몬과 라임 향이 어우러진 사이다", "Drink", 2.9, 33),
    LEMONADE("신선한 레몬의 상큼함이 가득한 레몬에이드", "Drink", 3.5, 33),
    AMERICANO("깊고 진한 풍미의 아메리카노, 깔끔한 마무리!", "Drink", 4.2, 33),

    //=====================================test=====================================================
    TEST("깊고 진한 풍미의 아메리카노, 깔끔한 마무리!", "test", 11, 33),
    TEST1("깊고 진한 풍미의 아메리카노, 깔끔한 마무리!", "test", 11, 33);

    public final String explanation;
    public final String type;
    public final double price;
    public final int discount;
    MenuEnum(String explanation, String type, double price, int discount) {
        this.explanation = explanation;
        this.type = type;
        this.price = price;
        this.discount = discount;
    }
    public String getExplanation(){
        return this.explanation;
    }
    public String getType(){
        return this.type;
    }
    public double getPrice(){
        return this.price;
    }
    public int getDiscount(){
        return this.discount;
    }
}
