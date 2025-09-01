package com.kh.statement.view;

import java.util.List;
import java.util.Scanner;

import com.kh.statement.controller.PlantController;
import com.kh.statement.model.vo.Plant;

public class PlantView {

    private Scanner sc = new Scanner(System.in);
    private PlantController pc = new PlantController();

    public void mainMenu() {
        while (true) {
            System.out.println("--- 식물 관리 ---");
            System.out.println("1. 식물 추가하기");
            System.out.println("2. 전체 식물 조회");
            System.out.println("3. 이름으로 검색");
            System.out.println("4. 키워드로 검색");
            System.out.println("0. 종료");
            System.out.print("메뉴를 선택해주세요 > ");
            int menuNo = sc.nextInt();
            sc.nextLine(); // 버퍼 정리

            switch (menuNo) {
                case 1:
                    save();
                    break;
                case 2:
                    findAll();
                    break;
                case 3:
                    findByName();
                    break;
                case 4:
                    findByKeyword();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 메뉴입니다.");
            }
        }
    }

    private void save() {
        System.out.println("=== 식물 추가하기 ===");

        System.out.print("식물 ID > ");
        int plantId = sc.nextInt();
        sc.nextLine();

        System.out.print("이름 > ");
        String name = sc.nextLine();

        System.out.print("학명 > ");
        String scientificName = sc.nextLine();

        System.out.print("과 > ");
        String family = sc.nextLine();

        System.out.print("색상 > ");
        String color = sc.nextLine();

        System.out.print("개화 시기 > ");
        String bloomSeason = sc.nextLine();

        System.out.print("높이(cm) > ");
        double heightCm = sc.nextDouble();
        sc.nextLine();

        System.out.print("원산지 > ");
        String origin = sc.nextLine();

        System.out.print("향기 > ");
        String fragrance = sc.nextLine();

        System.out.print("토양 종류 > ");
        String soilType = sc.nextLine();

        System.out.print("햇빛 > ");
        String sunlight = sc.nextLine();

        System.out.print("물 필요량 > ");
        String waterNeeds = sc.nextLine();

        System.out.print("관리 난이도 > ");
        String maintenanceLevel = sc.nextLine();

        System.out.print("설명 > ");
        String description = sc.nextLine();

        System.out.print("이미지 경로 > ");
        String imageUrl = sc.nextLine();

        int result = pc.save(plantId, name, scientificName, family, color, bloomSeason, heightCm, origin, fragrance,
                soilType, sunlight, waterNeeds, maintenanceLevel, description, imageUrl);

        if (result > 0) {
            System.out.println("식물 추가 성공!");
        } else {
            System.out.println("식물 추가 실패!");
        }
    }

    private void findAll() {
        System.out.println("\n=== 전체 식물 조회 ===");
        List<Plant> plants = pc.findAll();

        if (plants.isEmpty()) {
            System.out.println("조회 결과가 없습니다.");
        } else {
            for (Plant p : plants) {
                printPlant(p);
            }
        }
    }

    private void findByName() {
        System.out.print("\n검색할 식물 이름 > ");
        String name = sc.nextLine();

        Plant plant = pc.findByName(name);
        if (plant != null) {
            printPlant(plant);
        } else {
            System.out.println("존재하지 않는 식물입니다.");
        }
    }

    private void findByKeyword() {
        System.out.print("\n검색 키워드 > ");
        String keyword = sc.nextLine();

        List<Plant> plants = pc.findByKeyword(keyword);
        if (plants.isEmpty()) {
            System.out.println("조회 결과가 없습니다.");
        } else {
            for (int i = 0; i < plants.size(); i++) {
                System.out.println((i + 1) + "번 결과:");
                printPlant(plants.get(i));
            }
        }
    }

    private void printPlant(Plant p) {
        System.out.println("==============================");
        System.out.println("ID: " + p.getPlantId());
        System.out.println("이름: " + p.getName());
        System.out.println("학명: " + p.getScientificName());
        System.out.println("과: " + p.getFamily());
        System.out.println("색상: " + p.getColor());
        System.out.println("개화 시기: " + p.getBloomSeason());
        System.out.println("높이(cm): " + p.getHeightCm());
        System.out.println("원산지: " + p.getOrigin());
        System.out.println("향기: " + p.getFragrance());
        System.out.println("토양 종류: " + p.getSoilType());
        System.out.println("햇빛: " + p.getSunlight());
        System.out.println("물 필요량: " + p.getWaterNeeds());
        System.out.println("관리 난이도: " + p.getMaintenanceLevel());
        System.out.println("설명: " + p.getDescription());
        System.out.println("이미지 경로: " + p.getImageUrl());
        System.out.println("==============================\n");
    }
}
