package org.example.method;

import org.example.resource.KioskResource;
import org.example.resource.MenuEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResourceMethod {
    public SelectMethod selectMethod = new SelectMethod();
    public KioskResource kioskResource = new KioskResource();
    Scanner scanner = new Scanner(System.in);

    /**
     * 카테고리 및 메뉴 선택 맵을 설정하고 초기화하는 메서드
     * - 카테고리를 기반으로 메뉴를 분류하여 선택 기능 제공
     * - 카트 및 종료 기능도 포함
     */
    public void menuOrCategorySelectAdd() {
        int menuCount = 0; // 카테고리와 메뉴를 구분하는 카운터 변수
        String menuCategorySwitch = ""; // 현재 카테고리 상태를 저장하는 변수
        selectMethod.jobSelect();
        // ======================== 카테고리 선택 Map 초기화 ==========================
        for (MenuEnum menuEnum : MenuEnum.values()) { // 모든 메뉴를 순회
            List<Object> list = new ArrayList<>();
            if (!menuCategorySwitch.equals(menuEnum.type)) { // 새로운 카테고리 발견 시 처리
                menuCount++; // 카테고리 번호 증가
                menuCategorySwitch = menuEnum.type; // 현재 카테고리 업데이트

                // 카테고리 선택 시 실행할 Runnable 추가
                String currentCategory = menuEnum.type; // 현재 카테고리를 저장
                list.add((Runnable) () -> {
                    setupMenuSelectMap(currentCategory); // 해당 카테고리의 메뉴 설정
                    selectMethod.menuSelect(kioskResource.getMenuSelectMap()); // 메뉴 선택 화면 호출
                });
                list.add(menuEnum.type); // 카테고리 이름 추가
                kioskResource.getCategorySelectMap().put(menuCount, list); // 카테고리 선택 맵에 추가
            }
        }

        // =========================== 카트 및 종료 옵션 초기화 ==========================
        List<Object> list = new ArrayList<>();
        menuCount++;

        // 장바구니 선택 옵션 추가
        list.add((Runnable) () -> selectMethod.cartOrSellSelect(kioskResource.getCartList())); // 장바구니 처리
        list.add("구매 및 장바구니 수정");
        kioskResource.getCategorySelectMap().put(menuCount, list); // 카테고리 선택 맵에 추가
        List<Object> listt = new ArrayList<>();
        // 종료 옵션 추가
        menuCount++;
        listt.add((Runnable) () -> selectMethod.exitSelect()); // 종료 처리
        listt.add("종료");
        kioskResource.getCategorySelectMap().put(menuCount, listt);

        menuCount = 0; // 메뉴 카운트를 초기화

        // ======================== 메뉴 선택 Map 초기화 ==========================
        for (MenuEnum menuEnum : MenuEnum.values()) { // 모든 메뉴를 순회
            if (menuCount == 0) { // 맨 처음 "뒤로가기" 옵션 추가
                List<Object> listt_ = new ArrayList<>();
                listt_.add((Runnable) () -> selectMethod.categorySelect(kioskResource.getCategorySelectMap())); // 뒤로가기 실행
                listt_.add("뒤로가기");
                kioskResource.getMenuSelectMap().put(menuCount, listt_); // 메뉴 선택 맵에 추가
            }
            // 개별 메뉴 추가
            List<Object> list_ = new ArrayList<>();
            list_.add((Runnable) () -> selectMethod.cartSelect(menuEnum, kioskResource.getCartList())); // 메뉴 선택 처리
            list_.add(menuEnum); // 메뉴 정보 추가
            menuCount++;
            kioskResource.getMenuSelectMap().put(menuCount, list_); // 메뉴 선택 맵에 추가
        }
    }

    /**
     * 특정 카테고리에 속하는 메뉴를 메뉴 선택 맵에 설정하는 메서드
     *
     * @param category 선택된 카테고리 이름
     */
    private void setupMenuSelectMap(String category) {
        kioskResource.getMenuSelectMap().clear(); // 기존 메뉴 선택 맵 초기화
        int menuCount = 0; // 메뉴 카운트 초기화

        // 선택된 카테고리에 해당하는 메뉴만 추가
        for (MenuEnum menuEnum : MenuEnum.values()) {
            if (menuEnum.type.equals(category)) { // 카테고리 일치 여부 확인
                menuCount++;
                List<Object> list = new ArrayList<>();
                list.add((Runnable) () -> selectMethod.cartSelect(menuEnum, kioskResource.getCartList())); // 메뉴 선택 처리
                list.add(menuEnum); // 메뉴 정보 추가
                kioskResource.getMenuSelectMap().put(menuCount, list); // 메뉴 선택 맵에 추가
            }
        }

        // 뒤로가기 버튼 추가
        List<Object> backList = new ArrayList<>();
        backList.add((Runnable) () -> selectMethod.categorySelect(kioskResource.getCategorySelectMap())); // 뒤로가기 실행
        backList.add("뒤로가기");
        kioskResource.getMenuSelectMap().put(0, backList); // 메뉴 선택 맵에 추가
    }
}
