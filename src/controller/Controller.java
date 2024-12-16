package controller;

import model.*;

import java.util.ArrayList;

public class Controller {
    private static Controller controller = new Controller();
    private Controller(){}
    public static Controller getInstance(){ return controller;}


    public ArrayList<UserDTO> login(){

        return DAO.getInstance().login();
    }

    public boolean signup(UserDTO userDTO){


        return DAO.getInstance().signup(userDTO);
    }

    public ArrayList<OrderDTO> viewreservation(){

        return DAO.getInstance().viewreservation();
    }

    public boolean reservation(OrderDTO orderDTO){

        return DAO.getInstance().reservation(orderDTO);
    }

    public boolean deletereservation(String name,String phone){

        return DAO.getInstance().deletereservation(name,phone);
    }

    public void contentview(int choose){
        DAO.getInstance().contentview(choose);

    }

    public boolean reservationUser(OrderDTO orderDTO){

        return DAO.getInstance().reservationUser(orderDTO);
    }

    public boolean writeBoard(String title,String content,int usernum){

        return DAO.getInstance().writeBoard(title,content,usernum);
    }

    public boolean updateBoard(BoardDTO boardDTO,int choose){

        return DAO.getInstance().updateBoard(boardDTO,choose);
    }

    public boolean deleteBoard(int choose,int usernum){

        return DAO.getInstance().deleteBoard(choose,usernum);
    }

    public boolean deletereservationUser(int usernum){

        return DAO.getInstance().deletereservationUser(usernum);
    }

    public boolean deleteAccount(int usernum){

        return DAO.getInstance().deleteAccount(usernum);
    }

    public boolean deleteReservationAdmin(int choose){

        return DAO.getInstance().deleteReservationAdmin(choose);
    }

    public boolean updateBoardAdmin(BoardDTO boardDTO,int choose){

        return DAO.getInstance().updateBoardAdmin(boardDTO,choose);
    }

    public boolean deleteBoardAdmin(int choose){

        return DAO.getInstance().deleteBoardAdmin(choose);
    }

    public ArrayList<TireDTO> viewTire(){

        return DAO.getInstance().viewTire();
    }

    public boolean updateStock(int choose,int choose2){

        return DAO.getInstance().updateStock(choose,choose2);
    }

    public boolean addTire(TireDTO tireDTO){

        return DAO.getInstance().addTire(tireDTO);
    }









    //
}
