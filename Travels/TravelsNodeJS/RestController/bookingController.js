const bookingUtils = require('../Utils/bookingUtils');
const purchaseUtils = require('../Utils/purchaseUtils');
const userUtils = require('../Utils/userUtils');
const xportUtils = require('../Utils/xportUtils');
const utils = require('../Utils/utils');

module.exports = {
    getAllBookings: function(){
       return bookingUtils.getAllBookings();
    },

    getBookingById: function(id){
        return bookingUtils.getBookingById(id);
    },
    
    getBookingsByPurchaseId: function(purchase_id){
        return new Promise(async (resolve, reject) =>{
            try{
                const bookings = await bookingUtils.getBookingByPurchaseId(purchase_id);
                const bookingsPromises = bookings.map(async (booking) => {
                    let departureXport = await xportUtils.getXportById(booking.departure_xport_id);
                    booking.departureXport = departureXport;
                    let arrivalXport = await xportUtils.getXportById(booking.arrival_xport_id);
                    booking.arrivalXport = arrivalXport;

                });

                await Promise.all(bookingsPromises);
                console.log(bookings);
                resolve(bookings);

            } catch (error){
                reject(error);
            }
        }); 
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
