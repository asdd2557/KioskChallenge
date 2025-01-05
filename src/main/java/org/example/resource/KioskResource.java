package org.example.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class KioskResource {
    private Map<Integer, List<Object>> menuSelectMap = new HashMap<>();
    private Map<Integer, List<Object>> categorySelectMap = new HashMap<>();
    private Map<Integer, List<Object>> cartOrSellMap = new HashMap<>();
    private Map<Integer, List<Object>> jobMap = new HashMap<>();
    private String job;
    private Map<Integer, Runnable> cartMap = new HashMap<>();
    private List<MenuEnum> cartList = new ArrayList<>();
}
