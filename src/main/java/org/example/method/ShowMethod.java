package org.example.method;

import org.example.resource.MenuEnum;

import java.text.DecimalFormat;
import java.util.List;

public class ShowMethod {
    // 소수점 둘째 자리까지만 표시하기 위한 포맷팅 객체
    private final DecimalFormat DF = new DecimalFormat("#.##");

    public void showJob() {
        System.out.println();
        System.out.println("-----직업 선택-----");
        int menuCount = 0; // 카테고리 번호를 관리하는 변수
        String menuCategorySwitch = ""; // 이전에 출력한 카테고리를 저장

        // 모든 메뉴를 반복하면서 카테고리를 출력
        for (MenuEnum menu : MenuEnum.values()) {
            if (!menuCategorySwitch.equals(menu.job)) { // 새로운 카테고리인지 확인
                menuCount++; // 카테고리 번호 증가
                menuCategorySwitch = menu.job; // 현재 카테고리를 저장
                System.out.println(menuCount + ". " + menu.job); // 카테고리 출력
            }
        }

        // "장바구니"와 "종료" 항목을 추가로 출력
        menuCount++;
        System.out.println(menuCount + ". 종료");
    }

    // 카테고리를 화면에 출력하는 메소드
    public void showCategory() {
        System.out.println();
        System.out.println("-----카테고리-----");

        int menuCount = 0; // 카테고리 번호를 관리하는 변수
        String menuCategorySwitch = ""; // 이전에 출력한 카테고리를 저장

        // 모든 메뉴를 반복하면서 카테고리를 출력
        for (MenuEnum menu : MenuEnum.values()) {
            if (!menuCategorySwitch.equals(menu.type)) { // 새로운 카테고리인지 확인
                menuCount++; // 카테고리 번호 증가
                menuCategorySwitch = menu.type; // 현재 카테고리를 저장
                System.out.println(menuCount + ". " + menu.type); // 카테고리 출력
            }
        }

        // "장바구니"와 "종료" 항목을 추가로 출력
        menuCount++;
        System.out.println(menuCount + ". 장바구니");
        menuCount++;
        System.out.println(menuCount + ". 종료");
    }

    // 특정 카테고리의 메뉴를 화면에 출력하는 메소드
    public void showMenu(String menuType) {
        System.out.println();
        System.out.println("-----" + menuType + "-----"); // 선택한 카테고리 제목 출력

        int menuCount = 0; // 메뉴 번호를 관리하는 변수

        // 모든 메뉴를 반복하면서 선택한 카테고리에 속한 메뉴를 출력
        for (MenuEnum menu : MenuEnum.values()) {
            if (menuType.equals(menu.type)) { // 선택된 카테고리와 메뉴의 타입이 일치하는지 확인
                menuCount++; // 메뉴 번호 증가
                // 메뉴 정보 출력 (번호, 이름, 가격, 설명, 할인율)
                System.out.println(menuCount + ". " + menu + "   | W " + menu.getPrice()
                        + " | " + menu.getExplanation() + " | % " + menu.discount);
            }
        }

        // 선택 가능한 메뉴가 있으면 "뒤로가기" 옵션 추가
        if (menuCount != 0) {
            System.out.println("0. 뒤로가기");
        }
    }

    // 장바구니에 담긴 메뉴와 총 가격을 화면에 출력하는 메소드
    public void showCart(List<MenuEnum> cartList, String job) {
        double price = 0; // 장바구니의 총 가격을 저장
        int count = 0; // 장바구니 항목 번호를 관리

        System.out.println();
        System.out.println("-----장바구니-----");

        // 장바구니 리스트를 반복하면서 항목을 출력
        for (MenuEnum menuEnum : cartList) {
            count++; // 항목 번호 증가
            // 항목 정보 출력 (번호, 이름, 원래 가격, 할인율)
            System.out.println(count + ". " + menuEnum + " W" + menuEnum.price + " %" + menuEnum.discount);
            if(cartList.get(0).getJob().equals(job)){
                // 할인된 가격을 계산하고 총 가격에 추가
                price += menuEnum.price * (1 - ((double) menuEnum.discount / 100));
            }else{
                price += menuEnum.price;
            }

        }

        // 장바구니의 총 가격을 소수점 둘째 자리까지만 표시하여 출력
        System.out.println("총: " + DF.format(price) + "$");
        System.out.println();
    }
}
