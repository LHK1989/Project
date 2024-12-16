package view;

import controller.Controller;
import model.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
    private static View view = new View();
    private View(){}
    public static View getInstance(){ return view;}
    Scanner scan = new Scanner(System.in);

    public void mainpage(){
        while (true) {
            System.out.println(" ______________  ______   _____________   __________________ ");
            System.out.println("/_  __/  _/ __ \\/ ____/  / ____/ ____/ | / /_  __/ ____/ __ \\");
            System.out.println(" / /  / // /_/ / __/    / /   / __/ /  |/ / / / / __/ / /_/ /");
            System.out.println("/ / _/ // _, _/ /___   / /___/ /___/ /|  / / / / /___/ _, _/ ");
            System.out.println("/_/ /___/_/ |_/_____/   \\____/_____/_/ |_/ /_/ /_____/_/ |_|  ");
            System.out.println(" 1.로그인  2.회원가입  3.비회원예약  4.예약취소  5.게시판보기");
            int choose = scan.nextInt();
            if(choose==1){
                login();
            }else if(choose==2){
                signup();
            }else if(choose==3){
                reservation();
            }else if(choose==4){
                deletereservation();
            }else if(choose == 5){
                boardprint();
            }
            else{
                System.out.println(" 잘못된 번호 입니다. ");
            }
        }
    }

    public void admin(){    // 관리자 페이지
        System.out.println(" 관리자 페이지입니다.");
        while(true) {
            System.out.println(" 1.예약 관리 2.게시판 관리 3.재고 관리 4.로그아웃  ");
            int choose = scan.nextInt();
            if(choose == 1){
                while(true) {
                    System.out.println(" 1. 예약 확인 2. 예약 삭제 3. 이전 페이지 ");
                    int choose2 = scan.nextInt();
                    if(choose2 == 1){
                        viewreservation();
                    }else if(choose2 == 2){
                        deleteReservationAdmin();
                    }else if(choose2 == 3){
                        admin();
                    }else{
                        System.out.println(" 잘못된 번호 입니다.");
                    }
                }
            }else if(choose == 2){
                while (true){
                    System.out.println(" 1. 게시판 수정 2. 게시판 삭제 3. 이전 페이지");
                    int choose3 = scan.nextInt();
                    if(choose3 == 1){
                        updateBoardAdmin();
                    }else if(choose3 == 2){
                        deleteBoardAdmin();
                    }else if(choose3 == 3){
                        admin();
                    }else{
                        System.out.println(" 잘못된 번호입니다.");
                    }
                }

            }else if(choose == 3){
                while (true){
                    System.out.println("1. 재고 확인 2. 재고 수량 수정 3. 새로운 재고 입고 4. 이전 페이지");
                    int choose4 = scan.nextInt();
                    if(choose4 == 1){
                        viewTire();
                    }else if(choose4 == 2){
                        updateStock();
                    }else if(choose4 == 3){
                        addTire();
                    }else if(choose4 == 4){
                        admin();
                    }else{
                        System.out.println(" 잘못된 번호입니다.");
                    }
                }

            }else if(choose == 4){
                mainpage();
            }else{
                System.out.println(" 잘못된 번호 입니다.");
            }
        }
    }

    public void member(int usernum){   // 회원 페이지
        while (true){
            System.out.println("1. 예약하기 2. 게시판 관리 3. 예약취소 4. 회원탈퇴 5. 로그아웃  ");
            int choose = scan.nextInt();
            if(choose==1){
                reservationUser(usernum);
            }else if(choose==2){
                while (true){
                    System.out.println("1. 게시판 작성 2. 본인 게시판 수정 3. 본인 게시판 삭제 4. 이전 페이지");
                    int choose2 = scan.nextInt();
                    if(choose2==1){
                        writeBoard(usernum);
                    }else if(choose2==2){
                        updateBoard(usernum);
                    }else if(choose2==3){
                        deleteBoard(usernum);
                    }else if(choose2==4){
                        member(usernum);
                    }
                }
            }else if(choose==3){
                deletereservationUser(usernum);
            }else if(choose==4){
                deleteAccount(usernum);
                mainpage();
            }else if(choose==5){
                System.out.println("로그아웃 되었습니다.");
                mainpage();
            }else{
                System.out.println(" 잘못된 번호 입니다.");
            }
        }
    }

    public void login(){    // 로그인
        System.out.println(" 아이디를 입력하세요. ( 이전 화면 ='나가기'를 입력하세요 ) : ");
        String id = scan.next();
        if(id.equals("나가기")){
            mainpage();
        }
        System.out.println(" 비밀번호를 입력하세요 :");
        String password = scan.next();

        ArrayList<UserDTO> result = Controller.getInstance().login();

        int check = 0;
        int check2 = 0;

        for(int i= 0; i<=result.size()-1; i++){
            if(result.get(i).getId().equals(id)){
                check2=i;
                check+=2;
                break;
            }
        }

        if(result.get(check2).getPassword().equals(password)){
            check+=1;
        }

        if(check==3){
            System.out.println(" 환영합니다. " +id+"님");
            if(result.get(check2).getRole().equals("admin")){
                admin();
            }else{
                member(result.get(check2).getUsernum());
            }
        }else if(check==2){
            System.out.println(" 비밀번호가 다릅니다.");
            login();
        }else{
            System.out.println(" 등록되어있는 아이디가 없습니다.");
            login();
        }



    }

    public void signup(){   // 회원가입
        System.out.println(" 환영합니다. 회원 가입 페이지입니다. ");
        System.out.println("ID( 이전 화면 = ' 나가기 ' 입력 ) :");    String id = scan.next();
        if(id.equals("나가기")){
            mainpage();
        }
        ArrayList<UserDTO> list = DAO.getInstance().login();
        for(int i=0; i<= list.size()-1; i++){
            if(list.get(i).getId().equals(id) || id == null){
                System.out.println("이미 존재하는 아이디입니다.");
                signup();
            }
        }
        System.out.println(" 이름 :");   String name = scan.next();
        System.out.println(" 전화번호( '-'포함 13자 ) : "); String phonenumber = scan.next();
        System.out.println(" 주소 : "); String address = scan.next();
        System.out.println(" 비밀번호 :"); String password = scan.next();
        System.out.println(" 비밀번호확인 :"); String passwordcheck = scan.next();
        while(true) {
            if (!password.equals(passwordcheck) || password == null) {
                System.out.println(" 비밀번호가 다릅니다.");
                System.out.println(" 비밀번호 :"); password = scan.next();
                System.out.println(" 비밀번호확인 :"); passwordcheck = scan.next();
            }else{
                break;
            }
        }
        System.out.println(" 현재 타고다니는 차 : "); String car = scan.next();

        UserDTO userDTO = new UserDTO(id,name,phonenumber,address,password,car);

        boolean result = Controller.getInstance().signup(userDTO);

        if(result==true){
            System.out.println(" 회원이 등록되었습니다.");
        }else{
            System.out.println(" 등록 실패.");
        }

    }

    public void viewreservation(){ // 관리자용 예약 확인
        ArrayList<OrderDTO> result = Controller.getInstance().viewreservation();

        for(int i = 0; i<= result.size()-1; i++){
            System.out.printf("예약 번호 : %d ,예약 이름 : %s,핸드폰 번호 : %s, 예약 날짜 : %s\n ",
                    result.get(i).getOrdernum(),result.get(i).getName(),result.get(i).getPhone(),result.get(i).getOrderdate());
        }



    }
    public void reservation(){
        ArrayList<ServiceDTO> servicemenu = DAO.getInstance().seviceprint();



        System.out.println(" 비회원 예약 입니다. ");
        System.out.println(" 원하시는 날짜를 입력해주세요. 현재 날짜 :"+ LocalDateTime.now()+"\n희망 년도()를 입력해주세요 : ");
        int year = scan.nextInt();
        System.out.println(" 원하시는 월(month)를 입력해주세요 : ");
        int month = scan.nextInt();
        System.out.println(" 원하시는 날(day)를 입력해주세요 : ");
        int day = scan.nextInt();
        String callender = year+"-"+month+"-"+day;
        Timestamp date = Timestamp.valueOf(callender+" 00:00:00");

        DAO.getInstance().displayAvailableTimes(date);

        System.out.println(" 예약 할 시간을 입력해주세요( 09 ~ 18 ) : ");
        int hour = scan.nextInt();

        Timestamp finalTime = Timestamp.valueOf(callender + " " + hour + ":00:00");

        System.out.println(" 이름을 입력해주세요 (이전 화면 = '나가기' 입력 ) : ");   String name = scan.next();
        if(name.equals("나가기")){
            mainpage();
        }
        System.out.println(" 핸드폰 번호 :");    String phone = scan.next();
        System.out.println(" 원하시는 서비스 번호를 입력해주세요 : ");
        for(ServiceDTO serviceDTO : servicemenu){
            System.out.println(serviceDTO);
        }
        int service = scan.nextInt();
        if(service == 1){
            System.out.println(" 원하시는 브랜드 번호를 입력해주세요 :");
            ArrayList<BrandDTO> brandmenu = DAO.getInstance().brandprint();
            for(BrandDTO brandDTO : brandmenu){
                System.out.println(brandDTO);
            }
            int brand = scan.nextInt();
            System.out.println(" 본인 차량 사이즈 번호를 입력해주세요 :");
            ArrayList<SizeDTO> sizemenu = DAO.getInstance().sizeprint();
            for(SizeDTO sizeDTO : sizemenu){
                System.out.println(sizeDTO);
            }
            int size = scan.nextInt();
            System.out.println(" 원하시는 타이어 모델번호를 입력해주세요 :");
            ArrayList<TireDTO> tiremenu = DAO.getInstance().tireprint(brand,size);
            for(int i=0; i<=tiremenu.size()-1;i++){
                System.out.printf("번호 : %d , 이름 : %s, 가격 : %d , 재고 : %d \n"
                        ,tiremenu.get(i).getTirenum(),tiremenu.get(i).getName(),tiremenu.get(i).getPrice(),tiremenu.get(i).getStock());
            }
            int tirenum = scan.nextInt();
            int changenum = 0;
            while(true){
                System.out.println("원하시는 교체 수량을 입력해주세요 :");
                changenum = scan.nextInt();
                boolean stockcheck = DAO.getInstance().stockcheck(tirenum,changenum);
                if(stockcheck == true){
                    break;
                }else{
                    System.out.println(" 재고가 부족합니다.");
                }
            }
            OrderDTO orderDTO = new OrderDTO(finalTime,name,phone,service,tirenum,changenum);
            boolean result = Controller.getInstance().reservation(orderDTO);

            if(result == true){
                System.out.println(" 예약이 완료되었습니다.");
            }else{
                System.out.println(" 예약 오류 ");
            }



        }else{
            OrderDTO orderDTO = new OrderDTO(finalTime,name,phone,service);
            boolean result = Controller.getInstance().reservation(orderDTO);

            if(result == true){
                System.out.println(" 예약이 완료되었습니다.");
            }else{
                System.out.println(" 예약 오류 ");
            }
        }




    }

    public void deletereservation(){
        System.out.println(" 이름을 입력하세요( 이전화면 : '나가기'입력 ) : "); String name = scan.next();
        System.out.println(" 핸드폰 번호를 입력하세요 : ");    String phone = scan.next();

       boolean result = Controller.getInstance().deletereservation(name,phone);

       if(result == true){
           System.out.println(" 등록된 예약이 취소되었습니다. ");
       }else{
           System.out.println(" 등록된 예약이 없습니다.");
       }
    }


    public void boardprint(){

        ArrayList<BoardDTO> viewlist = DAO.getInstance().boardprint();
        if(viewlist != null) {
            for (int i = 0; i < viewlist.size() - 1; i++) {
                System.out.printf("번호 : %d , 제목 : %s , 작성 날짜 : %s \n", viewlist.get(i).getBoardid(), viewlist.get(i).getTitle(), viewlist.get(i).getBoarddate());
            }
        }else{
            System.out.println(" 게시판이 비어있습니다.");
            mainpage();
        }
        System.out.println(" 보고싶은 게시판 번호를 입력해주세요");
        int choose = scan.nextInt();

        Controller.getInstance().contentview(choose);


    }

    public void reservationUser(int usernum){
        ArrayList<ServiceDTO> servicemenu = DAO.getInstance().seviceprint();



        System.out.println(" 회원 예약 입니다. ");
        System.out.println(" 원하시는 날짜를 입력해주세요. 현재 날짜 :"+ LocalDateTime.now()+"\n희망 년도()를 입력해주세요 : ");
        int year = scan.nextInt();
        System.out.println(" 원하시는 월(month)를 입력해주세요 : ");
        int month = scan.nextInt();
        System.out.println(" 원하시는 날(day)를 입력해주세요 : ");
        int day = scan.nextInt();
        String callender = year+"-"+month+"-"+day;
        Timestamp date = Timestamp.valueOf(callender+" 00:00:00");

        DAO.getInstance().displayAvailableTimes(date);

        System.out.println(" 예약 할 시간을 입력해주세요( 09 ~ 18 ) : ");
        int hour = scan.nextInt();

        Timestamp finalTime = Timestamp.valueOf(callender + " " + hour + ":00:00");

        System.out.println(" 원하시는 서비스 번호를 입력해주세요 : ");
        for(ServiceDTO serviceDTO : servicemenu){
            System.out.println(serviceDTO);
        }
        int service = scan.nextInt();
        if(service == 1){
            System.out.println(" 원하시는 브랜드 번호를 입력해주세요 :");
            ArrayList<BrandDTO> brandmenu = DAO.getInstance().brandprint();
            for(BrandDTO brandDTO : brandmenu){
                System.out.println(brandDTO);
            }
            int brand = scan.nextInt();
            System.out.println(" 본인 차량 사이즈 번호를 입력해주세요 :");
            ArrayList<SizeDTO> sizemenu = DAO.getInstance().sizeprint();
            for(SizeDTO sizeDTO : sizemenu){
                System.out.println(sizeDTO);
            }
            int size = scan.nextInt();
            System.out.println(" 원하시는 타이어 모델번호를 입력해주세요 :");
            ArrayList<TireDTO> tiremenu = DAO.getInstance().tireprint(brand,size);
            for(int i=0; i<=tiremenu.size()-1;i++){
                System.out.printf("번호 : %d , 이름 : %s, 가격 : %d , 재고 : %d \n"
                        ,tiremenu.get(i).getTirenum(),tiremenu.get(i).getName(),tiremenu.get(i).getPrice(),tiremenu.get(i).getStock());
            }
            int tirenum = scan.nextInt();
            int changenum = 0;
            while(true){
                System.out.println("원하시는 교체 수량을 입력해주세요 :");
                changenum = scan.nextInt();
                boolean stockcheck = DAO.getInstance().stockcheck(tirenum,changenum);
                if(stockcheck == true){
                    break;
                }else{
                    System.out.println(" 재고가 부족합니다.");
                }
            }
            OrderDTO orderDTO = new OrderDTO(finalTime,usernum,service,tirenum,changenum);
            boolean result = Controller.getInstance().reservationUser(orderDTO);

            if(result == true){
                System.out.println(" 예약이 완료되었습니다.");
            }else{
                System.out.println(" 예약 오류 ");
            }



        }else{
            OrderDTO orderDTO = new OrderDTO(finalTime,usernum,service);
            boolean result = Controller.getInstance().reservationUser(orderDTO);

            if(result == true){
                System.out.println(" 예약이 완료되었습니다.");
            }else{
                System.out.println(" 예약 오류 ");
            }
        }
    }

    public void writeBoard(int usernum){
        System.out.println(" 게시판 제목을 입력해주세요 : ");
        String title = scan.next();
        scan.nextLine();
        System.out.println(" 내용을 입력해주세요 : ");

        String content = scan.nextLine();

        boolean result = Controller.getInstance().writeBoard(title,content,usernum);

        if(result == true){
            System.out.println(" 게시판이 작성되었습니다.");
        }else{
            System.out.println(" 게시판 작성 실패");
        }

    }

    public void updateBoard(int usernum){
        System.out.println(" 본인이 작성한 게시판 :");
        ArrayList<BoardDTO> viewBoard =DAO.getInstance().viewBoardUser(usernum);
        boolean result = false;
        int choose = 0;
        if(viewBoard != null) {
            for (int i = 0; i <= viewBoard.size() - 1; i++) {
                System.out.printf("번호 : %d , 제목 : %s\n ",viewBoard.get(i).getBoardid(),viewBoard.get(i).getTitle());
            }
            System.out.println(" 수정하고 싶은 번호를 입력하세요 : ");
            choose = scan.nextInt();
            System.out.println(" 제목 수정 : 1 , 내용 수정 : 2 , 전체 수정 : 3");
            int updateChoose = scan.nextInt();
            if(updateChoose == 1){
                System.out.println(" 변경 할 제목은 ?");
                String title = scan.next();
                BoardDTO boardDTO = new BoardDTO(choose,usernum,title);
                result = Controller.getInstance().updateBoard(boardDTO,updateChoose);
            }else if(updateChoose == 2){
                scan.nextLine();
                System.out.println(" 변경 할 내용은 ?");
                String content = scan.nextLine();
                BoardDTO boardDTO = new BoardDTO(content,choose,usernum);
                result = Controller.getInstance().updateBoard(boardDTO,updateChoose);
            }else if(updateChoose == 3){
                System.out.println(" 변경 할 제목은 ?");
                String title = scan.next();
                scan.nextLine();
                System.out.println(" 변경 할 내용은 ?");
                String content = scan.nextLine();
                BoardDTO boardDTO = new BoardDTO(choose,usernum,title,content);
                result = Controller.getInstance().updateBoard(boardDTO,updateChoose);
            }else {
                System.out.println(" 잘못된 번호 입니다.");
            }
            if(result == true){
                System.out.println(" 변경 완료 ");
            }else {
                System.out.println(" 게시판 번호랑 작성자가 맞지않습니다 ");
            }
        }else{
            System.out.println(" 작성한 게시판이 없습니다.");
        }

    }

    public void deleteBoard(int usernum){
        System.out.println(" 본인이 작성한 게시판 :");
        ArrayList<BoardDTO> viewBoard =DAO.getInstance().viewBoardUser(usernum);
        if(viewBoard != null) {
            for (int i = 0; i <= viewBoard.size() - 1; i++) {
                System.out.printf("번호 : %d , 제목 : %s\n ", viewBoard.get(i).getBoardid(), viewBoard.get(i).getTitle());
            }
            System.out.println(" 삭제 하실 게시판 번호 입력해주세요 :");
            int choose = scan.nextInt();
            boolean result = Controller.getInstance().deleteBoard(choose,usernum);

            if(result == true){
                System.out.println(" 삭제 완료");
            }else{
                System.out.println(" 게시판 번호랑 작성자가 맞지않습니다");
            }
        }else{
            System.out.println(" 작성한 게시판이 없습니다.");
        }
    }


    public void deletereservationUser(int usernum){
        boolean result = Controller.getInstance().deletereservationUser(usernum);

        if (result == true){
            System.out.println(" 등록된 예약이 삭제되었습니다. ");
        }else{
            System.out.println(" 등록된 예약이 없습니다.");
        }
    }

    public void deleteAccount(int usernum){
        System.out.println("회원을 탈퇴하시겠습니까? 탈퇴를 원하시면 : 1 아니면 2번을 입력해주세요.");
        int choose = scan.nextInt();
        if(choose==1) {
            boolean result = Controller.getInstance().deleteAccount(usernum);
            if(result == true){
                System.out.println(" 탈퇴가 완료되었습니다.");
            }else {
                System.out.println(" 탈퇴 실패");
            }
        }else if(choose==2){
            member(usernum);
        }else{
            System.out.println(" 잘못된 번호 입니다.");
            deleteAccount(usernum);
        }
    }

    public void deleteReservationAdmin(){
        ArrayList<OrderDTO> result = Controller.getInstance().viewreservation();

        for(int i = 0; i<= result.size()-1; i++){
            System.out.printf("예약 번호 : %d , 예약 날짜 : %s\n ",result.get(i).getOrdernum(),result.get(i).getOrderdate());
        }

        System.out.println("삭제 할 예약 번호는 ?");
        int choose = scan.nextInt();
        boolean result2 = Controller.getInstance().deleteReservationAdmin(choose);

        if(result2 == true){
            System.out.println(" 삭제 완료 ");
        }else{
            System.out.println(" 삭제 실패");
        }


    }

    public void updateBoardAdmin(){
        ArrayList<BoardDTO> viewlist = DAO.getInstance().boardprint();
        int choose = 0;
        boolean result = false;
        if(viewlist != null) {
            for (int i = 0; i < viewlist.size() - 1; i++) {
                System.out.printf("번호 : %d , 제목 : %s , 작성 날짜 : %s \n", viewlist.get(i).getBoardid(), viewlist.get(i).getTitle(), viewlist.get(i).getBoarddate());
            }
            System.out.println(" 수정 할 게시판은?");
            choose = scan.nextInt();
            System.out.println(" 제목 수정 : 1 , 내용 수정 : 2 , 전체 수정 : 3");
            int updateChoose = scan.nextInt();
            if(updateChoose == 1){
                System.out.println(" 변경 할 제목은 ?");
                String title = scan.next();
                BoardDTO boardDTO = new BoardDTO(choose,title);
                result = Controller.getInstance().updateBoardAdmin(boardDTO,updateChoose);
            }else if(updateChoose == 2){
                scan.nextLine();
                System.out.println(" 변경 할 내용은 ?");
                String content = scan.nextLine();
                BoardDTO boardDTO = new BoardDTO(choose,content);
                result = Controller.getInstance().updateBoardAdmin(boardDTO,updateChoose);
            }else if(updateChoose == 3){
                System.out.println(" 변경 할 제목은 ?");
                String title = scan.next();
                scan.nextLine();
                System.out.println(" 변경 할 내용은 ?");
                String content = scan.nextLine();
                BoardDTO boardDTO = new BoardDTO(choose,title,content);
                result = Controller.getInstance().updateBoardAdmin(boardDTO,updateChoose);
            }else {
                System.out.println(" 잘못된 번호 입니다.");
            }
            if(result == true){
                System.out.println(" 변경 완료 ");
            }else {
                System.out.println(" 변경 실패 ");
            }


        }else{
            System.out.println(" 게시판이 비어있습니다.");
            admin();
        }

    }

    public void deleteBoardAdmin(){
        ArrayList<BoardDTO> viewlist = DAO.getInstance().boardprint();
        if(viewlist != null) {
            for (int i = 0; i < viewlist.size() - 1; i++) {
                System.out.printf("번호 : %d , 제목 : %s , 작성 날짜 : %s \n", viewlist.get(i).getBoardid(), viewlist.get(i).getTitle(), viewlist.get(i).getBoarddate());
            }
            System.out.println(" 삭제 할 게시판은 ?");
            int choose = scan.nextInt();
            boolean result = Controller.getInstance().deleteBoardAdmin(choose);

            if(result==true){
                System.out.println(" 삭제 완료");
            }else {
                System.out.println(" 삭제 실패");
            }

        }else{
            System.out.println("게시판이 비어있습니다.");
        }
    }

    public void viewTire(){
        ArrayList<TireDTO> list = Controller.getInstance().viewTire();

        if (list != null){
            for (int i= 0; i<= list.size()-1; i++){
                System.out.printf("번호 : %d , 이름 : %s , 가격 : %d, 재고 : %d\n",list.get(i).getTirenum(),list.get(i).getName(),list.get(i).getPrice(),list.get(i).getStock());
            }
        }else {
            System.out.println("목록이 비어있습니다.");
        }
    }

    public void updateStock(){
        ArrayList<TireDTO> list = Controller.getInstance().viewTire();

        if (list != null){
            for (int i= 0; i<= list.size()-1; i++){
                System.out.printf("번호 : %d , 이름 : %s , 가격 : %d, 재고 : %d\n",list.get(i).getTirenum(),list.get(i).getName(),list.get(i).getPrice(),list.get(i).getStock());
            }
            System.out.println(" 변경 할 타이어 번호는 ?");
            int choose = scan.nextInt();
            System.out.println(" 변경 할 개수 :");
            int choose2 = scan.nextInt();
            boolean result = Controller.getInstance().updateStock(choose,choose2);

            if(result == true){
                System.out.println(" 변경 완료 ");
            }else {
                System.out.println(" 변경 실패 ");
            }
        }else {
            System.out.println(" 수정 할 목록이 비어있습니다.");
        }
    }

    public void addTire(){
        System.out.println(" 새로 입고 할 브랜드 번호를 입력해주세요 :");
        ArrayList<BrandDTO> brandmenu = DAO.getInstance().brandprint();
        for(BrandDTO brandDTO : brandmenu){
            System.out.println(brandDTO);
        }
        int brand = scan.nextInt();
        System.out.println(" 새로 입고 할 사이즈 번호를 입력해주세요 :");
        ArrayList<SizeDTO> sizemenu = DAO.getInstance().sizeprint();
        for(SizeDTO sizeDTO : sizemenu){
            System.out.println(sizeDTO);
        }
        int size = scan.nextInt();
        System.out.println(" 새로 입고 할 타이어 이름 : ");
        String name = scan.next();
        System.out.println(" 새로 입고 할 타이어 가격 : ");
        int price = scan.nextInt();
        System.out.println(" 새로 입고 할 타이어 개수 : ");
        int stock = scan.nextInt();

        TireDTO tireDTO = new TireDTO(brand,size,name,price,stock);
        boolean result = Controller.getInstance().addTire(tireDTO);

        if(result == true){
            System.out.println(" 등록 완료 ");
        }else{
            System.out.println(" 등록 실패 ");
        }

    }













}
