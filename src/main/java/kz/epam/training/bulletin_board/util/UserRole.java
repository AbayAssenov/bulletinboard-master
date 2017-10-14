package kz.epam.training.bulletin_board.util;

import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.constant.RoleConstant;

/**
 * @author Abay Assenov
 */

public class UserRole {

    public static String  getUserRole(int role){

        switch (role){
            case ParameterConstant.ONE: return RoleConstant.USER;
            case ParameterConstant.TWO: return RoleConstant.ADMIN;
            default: return RoleConstant.GUEST;
        }
    }
}
