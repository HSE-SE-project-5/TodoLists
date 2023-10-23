package hse.todolist.databaseInteractor;

import hse.todolist.entities.List;
import hse.todolist.entities.*;
import hse.todolist.databaseInteractor.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*ListService ls = new ListService();
        ls.addNewListWithAllParams("todolist", "created", "1) code \n 2) profit.");
        ls.closeHandler();*/
        UserService us = new UserService();
        us.addNewUserWithAllParams("biba2", "boba2", "");
        us.closeHandler();
        /*ListService ls = new ListService();
        java.util.List<Integer> gayList = new ArrayList<>();
        gayList.add(1);
        gayList.add(2);
        java.util.List<List> result = ls.getListsWithListIds(gayList);
        for (List l : result) {
            System.out.println(l.getContent());
        }
        ls.closeHandler();*/
    }
}
