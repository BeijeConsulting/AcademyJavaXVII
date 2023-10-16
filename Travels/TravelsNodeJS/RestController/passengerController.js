const passengerUtils = require('../Utils/passengerUtils');

module.exports = {
    getPassengersByPurchaseId: function(purchase_id){
        return passengerUtils.getPassengersByPurchaseId(purchase_id);
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