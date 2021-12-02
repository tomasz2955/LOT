package com.example.LOT;

import java.util.ArrayList;
import java.util.List;

import static com.example.LOT.Code.ACCOUNT_NOT_FOUND;

public class MessageCodes {

    private List<MessageCode> codes = new ArrayList<>();

    public MessageCodes() {
        codes.add(new MessageCode(ACCOUNT_NOT_FOUND, "Account does not exist."));
    }

    public static String findByCode(Code code) {
//        codes.stream().filter(c -> )
        return "napraw mnie";
    }

}

