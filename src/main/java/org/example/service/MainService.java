package org.example.service;

import org.example.method.ResourceMethod;
import org.example.method.SelectMethod;

public class MainService {

    SelectMethod selectMethod = new SelectMethod();
    ResourceMethod resourceMethod = new ResourceMethod();
public void Start(){
     resourceMethod.menuOrCategorySelectAdd();//키오스크 실행에 필요한 변수들 초기화
    while (true){
        selectMethod.categorySelect(resourceMethod.kioskResource.getCategorySelectMap()); //카테고리 선택하는것부터 실행
    }


}

}
