package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Connection conn;

    private static DAO dao = new DAO();

    private DAO(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project2","root", "1234");
            System.out.println(" BoardDB Connection OK");
        }catch (ClassNotFoundException e){
            e.getException();
            System.out.println(" BoardDB Connection fail");
        }catch (SQLException e){
            e.getMessage();
            System.out.println(" BoardDB Connection fail");
        }
    }

    public static DAO getInstance(){ return dao;}


public ArrayList<UserDTO> login(){

    ArrayList<UserDTO> list = new ArrayList<>();

    try{
        String sql = "select * from user";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs= ps.executeQuery();

        while (rs.next()){
            int usernum = rs.getInt("usernum");
            String id = rs.getString("id");
            String password = rs.getString("password");
            String role = rs.getString("role");

            UserDTO userDTO = new UserDTO(id,password,role,usernum);

            list.add(userDTO);
        }
    }catch (SQLException e){
        e.getMessage();
    }




    return list;
}


public boolean signup(UserDTO userDTO){
        try{

            String sql = "insert into user(id,name, phonenumber, address, password, car) values (?,?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,userDTO.getId());
            ps.setString(2,userDTO.getName());
            ps.setString(3,userDTO.getPhonenumber());
            ps.setString(4,userDTO.getAddress());
            ps.setString(5,userDTO.getPassword());
            ps.setString(6,userDTO.getCar());
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.getMessage();
        }
        return false;
}

public ArrayList<OrderDTO> viewreservation(){

        ArrayList<OrderDTO> list = new ArrayList<>();

        try{
            String sql = " select * from orders order by orderdate asc";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int ordernum = rs.getInt("ordernum");
                int orderusernum = rs.getInt("orderusernum");
                Timestamp orderdate = rs.getTimestamp("orderdate");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                int servicetype = rs.getInt("servicetype_fk");
                int tire = rs.getInt("tire_fk");

                OrderDTO orderDTO = new OrderDTO(ordernum,orderusernum,orderdate,name,phone,servicetype,tire);

                list.add(orderDTO);

            }
        }catch (SQLException e){
            e.getMessage();
        }

        return list;
}

public ArrayList<ServiceDTO> seviceprint(){  // 서비스 출력

        ArrayList<ServiceDTO> list = new ArrayList<>();
        try {
            String sql = " select * from service";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int serviceid = rs.getInt("serviceid");
                String servicename = rs.getNString("servicename");
                int price = rs.getInt("price");

                ServiceDTO serviceDTO = new ServiceDTO(serviceid,servicename,price);

                list.add(serviceDTO);
            }

        }catch (SQLException e){
            e.getMessage();
        }

        return list;
}

public ArrayList<BrandDTO> brandprint(){

        ArrayList<BrandDTO> list = new ArrayList<>();

        try{
            String sql = "select * from brand";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int brandid = rs.getInt("brandid");
                String brandname = rs.getString("brandname");

                BrandDTO brandDTO = new BrandDTO(brandid,brandname);

                list.add(brandDTO);
            }
        }catch (SQLException e){
            e.getMessage();
        }


        return list;
}

public ArrayList<SizeDTO> sizeprint(){

        ArrayList<SizeDTO> list = new ArrayList<>();

        try {
            String sql = "select * from size";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int sizeid = rs.getInt("sizeid");
                String size = rs.getString("size");

                SizeDTO sizeDTO = new SizeDTO(sizeid,size);

                list.add(sizeDTO);
            }
        }catch (SQLException e){
            e.getMessage();
        }

        return list;
}

public ArrayList<TireDTO> tireprint(int brand, int size){

        ArrayList<TireDTO> list = new ArrayList<>();

        try {
            String sql = " select * from tire where brandid_fk = ? and size_fk = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,brand);
            ps.setInt(2,size);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int tirenum = rs.getInt("tirenum");
                String tirename = rs.getString("tirename");
                int price = rs.getInt("price");
                int stock = rs.getInt("stock");

                TireDTO tireDTO = new TireDTO(tirenum,tirename,price,stock);

                list.add(tireDTO);
            }

        }catch (SQLException e){
            e.getMessage();
        }

        return list;
}

public boolean stockcheck(int tirenum,int changenum){
        try {
            String sql = "select stock from tire where tirenum = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,tirenum);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                int stock = rs.getInt("stock");

                if(changenum > stock){
                    return false;
                }
            }


        }catch (SQLException e){
            e.getMessage();
        }

        return true;
}

public List<Timestamp> checktime(Timestamp timestamp){
        List<Timestamp> availableTime = new ArrayList<>();

        try{

            String sql = "select orderdate from orders where date(orderdate) = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setTimestamp(1,timestamp);

            ResultSet rs = ps.executeQuery();

            List<Timestamp> checkTime = new ArrayList<>();

            while(rs.next()){
                Timestamp date = rs.getTimestamp("orderdate");
                checkTime.add(date);
            }
            for (int i = 9; i < 18; i++) {
                Timestamp possibleTime = Timestamp.valueOf(timestamp.toLocalDateTime().withHour(i).withMinute(0).withSecond(0).withNano(0));

                if (!checkTime.contains(possibleTime)){
                    availableTime.add(possibleTime);
                }

            }


        }catch (SQLException e){
            e.getMessage();
        }

        return availableTime;
}



public void displayAvailableTimes(Timestamp requestedDate) {
        List<Timestamp> availableTimes = checktime(requestedDate);

        if (availableTimes.isEmpty()) {
            System.out.println("예약 가능한 시간이 없습니다.");
        } else {
            System.out.println("예약 가능한 시간대:");
            for (Timestamp time : availableTimes) {
                System.out.println(time.toString());
            }
        }
}

public boolean reservation(OrderDTO orderDTO){
        if(orderDTO.getServicetype()==1){

            try {
                String sql = "insert into orders(orderdate,name,phone,servicetype_fk,tire_fk,quantity) values (?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setTimestamp(1,orderDTO.getOrderdate());
                ps.setString(2,orderDTO.getName());
                ps.setString(3,orderDTO.getPhone());
                ps.setInt(4,orderDTO.getServicetype());
                ps.setInt(5,orderDTO.getTire());
                ps.setInt(6,orderDTO.getQuantity());

                ps.executeUpdate();
                return true;
            }catch (SQLException e){
                e.getMessage();
            }
        }else{
            try {
                String sql = "insert into orders(orderdate,name,phone,servicetype_fk) values (?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setTimestamp(1,orderDTO.getOrderdate());
                ps.setString(2,orderDTO.getName());
                ps.setString(3,orderDTO.getPhone());
                ps.setInt(4,orderDTO.getServicetype());

                ps.executeUpdate();
                return true;
            }catch (SQLException e){
                e.getMessage();
            }
        }
    return false;
}

public boolean deletereservation(String name,String phone){
        try{
            String sql = "delete from orders where name = ? and phone = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,phone);
            ps.executeUpdate();

            return true;
        }catch (SQLException e){
            e.getMessage();
        }
        return false;
}

public ArrayList<BoardDTO> boardprint(){

        ArrayList<BoardDTO> list = new ArrayList<>();

        try {
            String sql = "select boardid,title,board_date from board";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int boardid = rs.getInt("boardid");
                String title = rs.getString("title");
                Timestamp date = rs.getTimestamp("board_date");

                BoardDTO boardDTO = new BoardDTO(boardid,title,date);
                list.add(boardDTO);

            }
        }catch (SQLException e){
            e.getMessage();
        }
        return list;
}

public void contentview(int choose){
        try {
            String sql = "select content from board where boardid=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,choose);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String content = rs.getString("content");

                System.out.println(content);
            }else{
                System.out.println(" 해당 번호가 잘못되었습니다.");
            }
        }catch (SQLException e){
            e.getMessage();
        }


}

public boolean reservationUser(OrderDTO orderDTO){
    if(orderDTO.getServicetype()==1){

        try {
            String sql = "insert into orders(orderdate,orderusernum,servicetype_fk,tire_fk,quantity) values (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setTimestamp(1,orderDTO.getOrderdate());
            ps.setInt(2,orderDTO.getOrderusernum());
            ps.setInt(3,orderDTO.getServicetype());
            ps.setInt(4,orderDTO.getTire());
            ps.setInt(5,orderDTO.getQuantity());

            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.getMessage();
        }
    }else{
        try {
            String sql = "insert into orders(orderdate,orderusernum,servicetype_fk) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setTimestamp(1,orderDTO.getOrderdate());
            ps.setInt(2,orderDTO.getOrderusernum());
            ps.setInt(3,orderDTO.getServicetype());

            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.getMessage();
        }
    }
    return false;


}


public boolean writeBoard(String title,String content,int usernum){

        try{
            String sql = "insert into board(writeuser,title,content) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,usernum);
            ps.setString(2,title);
            ps.setString(3,content);
            ps.executeUpdate();

            return true;
        }catch (SQLException e){
            e.getMessage();
        }
        return false;
}

public ArrayList<BoardDTO> viewBoardUser(int usernum){
        ArrayList<BoardDTO> list = new ArrayList<>();
        try {
            String sql = "select * from board where writeuser = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,usernum);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int boardid = rs.getInt("boardid");
                String title = rs.getString("title");

                BoardDTO boardDTO = new BoardDTO(boardid,title);
                list.add(boardDTO);
            }
        }catch (SQLException e){
            e.getMessage();
        }

        return list;
}

public boolean updateBoard(BoardDTO boardDTO,int choose){
    try {

    if (choose == 1) {

        String sql = "update board set title = ? where boardid = ? and writeuser = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, boardDTO.getTitle());
        ps.setInt(2, boardDTO.getBoardid());
        ps.setInt(3, boardDTO.getWriteuser());

        ps.executeUpdate();
        return true;
    } else if (choose == 2) {
        String sql = "update board set content = ? where boardid = ? and writeuser = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, boardDTO.getContent());
        ps.setInt(2,boardDTO.getBoardid());
        ps.setInt(3,boardDTO.getWriteuser());

        ps.executeUpdate();
        return true;
    } else if(choose ==3){
        String sql ="update board set title = ?, content = ? where boardid = ? and writeuser = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1,boardDTO.getTitle());
        ps.setString(2,boardDTO.getContent());
        ps.setInt(3,boardDTO.getBoardid());
        ps.setInt(4,boardDTO.getWriteuser());

        ps.executeUpdate();
        return true;
    }else{
        return false;
    }
    }catch (SQLException e){
    e.getMessage();
    }
    return false;
}

public boolean deleteBoard(int choose,int usernum){
        try {
            String sql = "delete from board where boardid = ? and writeuser = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,choose);
            ps.setInt(2,usernum);

            ps.executeUpdate();
            return true;

        }catch (SQLException e){
            e.getMessage();
        }
        return false;
}

public boolean deletereservationUser(int usernum){
        try {
            String sql = " delete from orders where orderusernum = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,usernum);

            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.getMessage();
        }
        return false;
}

public boolean deleteAccount(int usernum){
        try {
            String sql = " delete from user where usernum = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,usernum);

            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.getMessage();
        }
        return false;
}

public boolean deleteReservationAdmin(int choose){
        try {
            String sql = "delete from orders where ordernum = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,choose);

            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.getMessage();
        }
        return false;
}

public boolean updateBoardAdmin(BoardDTO boardDTO,int choose){
    try {

        if (choose == 1) {

            String sql = "update board set title = ? where boardid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, boardDTO.getTitle());
            ps.setInt(2, boardDTO.getBoardid());

            ps.executeUpdate();
            return true;
        } else if (choose == 2) {
            String sql = "update board set content = ? where boardid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, boardDTO.getTitle());
            ps.setInt(2,boardDTO.getBoardid());

            ps.executeUpdate();
            return true;
        } else if(choose ==3){
            String sql ="update board set title = ?, content = ? where boardid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,boardDTO.getTitle());
            ps.setString(2,boardDTO.getContent());
            ps.setInt(3,boardDTO.getBoardid());

            ps.executeUpdate();
            return true;
        }else{
            return false;
        }
    }catch (SQLException e){
        e.getMessage();
    }
    return false;
}

public boolean deleteBoardAdmin(int choose){
        try {
            String sql = "delete from board where boardid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,choose);

            ps.executeUpdate();

            return true;
        }catch (SQLException e){

        }

        return false;
}

public ArrayList<TireDTO> viewTire(){
        ArrayList<TireDTO> list = new ArrayList<>();
        try {
            String sql = "select * from tire";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                int tirenum = rs.getInt("tirenum");
                String tirename = rs.getString("tirename");
                int price = rs.getInt("price");
                int stock = rs.getInt("stock");

                TireDTO tireDTO = new TireDTO(tirenum,tirename,price,stock);
                list.add(tireDTO);

            }
        }catch (SQLException e){
            e.getMessage();
        }
        return list;
}

public boolean updateStock(int choose,int choose2){
        try {
            String sql = "update tire set stock = ? where tirenum = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,choose2);
            ps.setInt(2,choose);

            ps.executeUpdate();

            return true;
        }catch (SQLException e){
            e.getMessage();
        }

        return false;

}

public boolean addTire(TireDTO tireDTO){
        try {
            String sql = "insert into tire(brandid_fk,size_fk,tirename,price,stock) values (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1,tireDTO.getBrand());
            ps.setInt(2,tireDTO.getSize());
            ps.setString(3,tireDTO.getName());
            ps.setInt(4,tireDTO.getPrice());
            ps.setInt(5,tireDTO.getStock());

            ps.executeUpdate();

            return true;
        }catch (SQLException e){
            e.getMessage();
        }

        return false;
}














    //
}
