package com.ll.wiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner;
    List<WiseSaying> wiseSayings;
    int count;


    public App() {
        scanner = new Scanner(System.in);
        wiseSayings = new ArrayList<>();
        count = 0;
    }
    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령 ) ");
            String cmd = scanner.nextLine();

            if (cmd.startsWith("삭제?id=")) {
                delete(cmd);
                continue;
            }

            if (cmd.startsWith("수정?id=")) {
                modify(cmd);
                continue;
            }

            switch (cmd) {
                case "등록":
                    add();
                    break;
                case "목록":
                    list();
                    break;
                case "종료":
                    System.out.println("게시판이 종료됩니다.");
                    return;
            }
        }
    }
    public void add() {
        System.out.print("명언 : ");
        String comment = scanner.nextLine();

        System.out.print("작가 : ");
        String author = scanner.nextLine();

        wiseSayings.add(new WiseSaying(++count,author,comment));
        System.out.println(count+ "번 명령이 등록되었습니다.");
    }

    public void list() {
        System.out.println("번호 / 작가 / 명언");

        // 비효율적인 느낌 차라리 향상된 for문이 가독성이 더 좋고 간결해보임
        wiseSayings.stream()
                .sorted((a , b) -> Integer.compare(b.getId(),a.getId()))
                .forEach(e -> System.out.println(e.getId() + " / " + e.getAuthor() + " / " + e.getComment()));
//
//        for (WiseSaying wiseSaying : wiseSayings.reversed()) {
//            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getComment());
//        }
    }

    public void delete(String cmd) {
        String div = cmd.split("=", 2)[1];
        try {
            int convert = Integer.parseInt(div);
            boolean removed = wiseSayings.removeIf(e -> e.getId() == convert);

            if (removed) {
                System.out.println(convert + "번 명언이 삭제되었습니다.");
            } else {
                System.out.println(convert + "번 명언은 존재하지 않습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("올바른 명령어가 아닙니다. 삭제?id=번호 형식으로 입력해 주세요.");
        }
    }

    public void modify(String cmd) {
        try {
            String div = cmd.split("=", 2)[1];
            int convert = Integer.parseInt(div);
//            WiseSaying wiseSaying = null;

            WiseSaying wiseSaying = wiseSayings.stream()
                    .filter(e -> e.getId() == convert)
                    .findFirst()
                    .orElse(null);

//            for (WiseSaying saying : wiseSayings) {
//                if (saying.getId() == convert) {
//                    wiseSaying = saying;
//                }
//            }

            if (wiseSaying == null) {
                System.out.println("존재하지 않는 회원입니다.");
                return;
            }
            System.out.println("명언(기존) : " + wiseSaying.getComment());
            System.out.print("명언 : ");
            String comment = scanner.nextLine();

            System.out.println("작가(기존) : " + wiseSaying.getAuthor());
            System.out.print("작가 : ");
            String author = scanner.nextLine();

            wiseSaying.setComment(comment);
            wiseSaying.setAuthor(author);
            System.out.println(convert + "번 명령어가 수정되었습니다.");

        } catch (NumberFormatException e) {
            System.out.println("올바른 명령어가 아닙니다. 수정?id=번호 형식으로 입력해 주세요.");
        }
    }
}
