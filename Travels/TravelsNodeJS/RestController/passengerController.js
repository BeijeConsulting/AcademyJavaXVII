const passengerUtils = require('../Utils/passengerUtils');

module.exports = {
    getPassengersByPurchase: function(purchase){
        return passengerUtils.getPassengersByPurchase(purchase);
    },
    getTravelPassengers: function(travel_id){
        return passengerUtils.getTravelPassengers(travel_id);
    },
    getPassengerById: function(id){
        return passengerUtils.getPassengerById(id);
    },
    getAllPassengers: function(){
        return passengerUtils.getAllPassengers();
    }
}