const bookingUtils = require('../Utils/bookingUtils');
const purchaseUtils = require('../Utils/purchaseUtils');
const userUtils = require('../Utils/userUtils');

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

    getBookingsByTravelId: function(travel_id){
        return new Promise(async (resolve, reject) => {
            try {
                const bookings = await bookingUtils.getBookingsByTravelId(travel_id);
                const bookingsPromises = bookings.map(async (booking) => {
                   let purchase = await purchaseUtils.getPurchaseById(booking.purchase_id);
                   let user = await userUtils.getUserById(purchase.user_id);
                   booking.purchase_user = user;
                   //console.log(user);
                });

                await Promise.all(bookingsPromises);
                
                console.log(bookings);
                resolve(bookings);
            } catch (error) {
                reject(error);
            }
        });
    }

}
