package org.example.method;

import org.example.resource.MenuEnum;

import java.util.Map;
import java.util.Scanner;
import java.util.List;

public class SelectMethod {

    Scanner scanner = new Scanner(System.in); // 사용자 입력 처리
    ShowMethod showMethod = new ShowMethod(); // 출력 관련 메소드 관리
    String job;

    public void jobSelect() {
        showMethod.showJob();
        Scanner scanner = new Scanner(System.in);
        int select =  scanner.nextInt();
        switch (select) {
            case 1:
                job = "군인";
                break;
            case 2:
                job = "학생";
                break;
            case 3:
                System.exit(0); // 정상 종료
                break;
            default:
                System.out.println("잘못 입력 하셨습니다.");
                jobSelect();
        }

    }

    // 카테고리를 선택하고 해당 카테고리에 대한 메뉴를 보여주는 메소드
    public void categorySelect(Map<Integer, List<Object>> categorySelectMap) {

        // 현재 카테고리를 화면에 출력
        showMethod.showCategory();

        try {
            // 사용자 입력을 받아 선택된 카테고리를 처리
            int select = scanner.nextInt();

            // 선택한 카테고리의 이름을 출력
            System.out.println(categorySelectMap.get(select).get(1) + "을 선택하셨습니다.");

            // 선택한 카테고리에 포함된 메뉴를 화면에 출력
            showMethod.showMenu((String) categorySelectMap.get(select).get(1));

            // 해당 카테고리의 실행 이벤트를 가져와 실행
            Runnable event = (Runnable) categorySelectMap.get(select).get(0);
            event.run();
        } catch (Exception e) {
            // 잘못된 입력이 들어온 경우 처리
            System.out.println("숫자를 잘못 입력하셨습니다. 다시 입력해주세요.");
            categorySelect(categorySelectMap); // 메소드 재호출로 다시 입력받기
        }
    }

    // 특정 메뉴를 선택하고 관련 이벤트를 실행하는 메소드
    public void menuSelect(Map<Integer, List<Object>> menuSelectMap) {
        // 사용자 입력을 받아 선택된 메뉴 처리
        int select = scanner.nextInt();

        try {
            // 선택된 메뉴 이름을 출력
            System.out.println(menuSelectMap.get(select).get(1) + "을 선택하셨습니다.");
            System.out.println();

            // 선택된 메뉴의 실행 이벤트를 가져와 실행
            Runnable event = (Runnable) menuSelectMap.get(select).get(0);
            event.run();
        } catch (Exception e) {
            // 잘못된 입력이 들어온 경우 처리
            System.out.println("숫자를 잘못 입력하셨습니다. 다시 입력해주세요.");
            menuSelect(menuSelectMap); // 메소드 재호출로 다시 입력받기
        }
    }


    // 사용자가 선택한 메뉴를 장바구니에 추가하거나 취소하는 메소드
    public void cartSelect(MenuEnum menuEnum, List<MenuEnum> cartList) {
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인      2. 취소");

        // 사용자 입력을 받아 처리
        int select = scanner.nextInt();
        if (select == 1) {
            // 장바구니에 선택한 메뉴를 추가
            cartList.add(menuEnum);

            // 장바구니 내용을 화면에 출력
            showMethod.showCart(cartList, job);
        } else if (select == 2) {
            // 취소 선택 시 아무 작업도 하지 않고 종료
            return;
        } else {
            // 잘못된 입력 처리
            System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            cartSelect(menuEnum, cartList); // 메소드 재호출로 다시 입력받기
        }
    }

    // 장바구니와 구매 옵션을 관리하는 메소드
    public void cartOrSellSelect(List<MenuEnum> cartList) {
        System.out.println("0. 뒤로가기");
        System.out.println("1. 구매");
        System.out.println("2. 장바구니");

        // 사용자 입력을 받아 처리
        int select = scanner.nextInt();
        if (select == 1) {
            // 구매 선택 시
            if (cartList.isEmpty()) {
                // 장바구니가 비어있으면 메시지를 출력
                System.out.println("품목이 없습니다.");
                return;
            }
            // 구매 완료 처리 후 장바구니 비우기
            cartList.clear();
            System.out.println("구입이 완료되었습니다.");
        } else if (select == 2) {
            // 장바구니 보기 선택 시 장바구니 내용을 출력
            showMethod.showCart(cartList,job);

            // 장바구니에서 품목 삭제 기능 제공
            cartDeleteSelect(cartList);

            // 다시 장바구니 또는 구매 옵션 표시
            cartOrSellSelect(cartList);
        } else if (select == 0) {
            // 뒤로가기 선택 시 아무 작업도 하지 않고 종료
            return;
        } else {
            // 잘못된 입력 처리
            System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            cartOrSellSelect(cartList); // 메소드 재호출로 다시 입력받기
        }
    }

    // 장바구니에서 품목을 삭제하는 메소드
    public void cartDeleteSelect(List<MenuEnum> cartList) {
        try {
            System.out.println();
            System.out.println("삭제할 품목을 선택하여 주세요");
            System.out.println("0. 뒤로가기");
            System.out.println();

            // 장바구니 내용을 출력
            showMethod.showCart(cartList,job);

            // 사용자 입력을 받아 처리
            int select = scanner.nextInt();
            if (select == 0) {
                // 뒤로가기 선택 시 종료
                return;
            }
            // 입력받은 번호에 해당하는 품목을 장바구니에서 삭제
            cartList.remove(select - 1);
            System.out.println();
            System.out.println("삭제되었습니다.");
            System.out.println();

            // 업데이트된 장바구니 내용을 출력
            showMethod.showCart(cartList,job);
        } catch (Exception e) {
            // 잘못된 입력 처리
            System.out.println("숫자를 잘못 입력하셨습니다. 다시 입력해 주세요.");
        }
    }

    // 프로그램을 종료하는 메소드
    public void exitSelect() {
        System.out.println("종료됩니다.");
        System.exit(0); // 프로그램 종료
    }
}
