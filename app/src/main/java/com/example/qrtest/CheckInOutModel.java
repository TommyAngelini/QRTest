package com.example.qrtest;

public class CheckInOutModel {
    /** This class handles the business logic of checking in / out */

    public String queryQRToBuilding(int id){
        /** Take building ID from QR scan and return building name */

        return "VKC";
    }

    public int queryGetBuildingCapacity(int id){
        /** Take building ID and return max number of students */

        return 50;
    }

    public boolean queryBuildingIsFull(int id){
        /** Return true if building is at capacity, false if not */

        return false;
    }

    public boolean checkInUser(int userID, int buildingID){
        /** Check in user to database, logged in to the corresponding building */

        return true;
    }

    public boolean checkOutUser(int userID, int buildingID){
        /** Check out user in database, log out from corresponding building */

        return false;
    }

    public boolean userIsCheckedIn(int userID){
        /** Returns true if user already checked in to another building */

        return false;
    }
}