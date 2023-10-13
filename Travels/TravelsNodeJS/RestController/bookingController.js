const bookingUtils = require('../Utils/bookingUtils');

module.exports = {
    getAllBookings: function(){
       return bookingUtils.getAllBookings();
    },

    getBookingById: function(id){
        return bookingUtils.getBookingById(id);
    },
    
    getBookingByPurchaseId: function(purchase_id){
        return bookingUtils.getBookingByPurchaseId(purchase_id);
    },

    getBookingByTravelId: function(travel_id){
        return bookingUtils.getBookingByTravelId(travel_id);
    }

}
