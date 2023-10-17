const bookingUtils = require('../Utils/bookingUtils');
const purchaseUtils = require('../Utils/purchaseUtils');
const userUtils = require('../Utils/userUtils');
const xportUtils = require('../Utils/xportUtils');
const utils = require('../Utils/utils');
const scheduleRouteUtils = require('../Utils/scheduleRouteUtils');

module.exports = {
    getAllBookings: function(){
       return bookingUtils.getAllBookings();
    },

    getBookingById: function(id){
        return bookingUtils.getBookingById(id);
    },
    
    getBookingsByPurchaseId: function(purchase_id){
        return bookingUtils.getBookingsByPurchaseId(purchase_id);
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
    },

    createBooking: function(data){
        return new Promise(async (resolve, reject) => {
            try{
                let scheduleId = data.scheduleId;
                let schedule = await scheduleRouteUtils.getScheduleById(scheduleId);
                
                let departure_xport = schedule.departure_xport;
                let arrival_xport = schedule.arrival_xport;

                let departure_date = data.departure_date;
                const dep = new Date(departure_date);
                let arrival_date = dep;
                arrival_date.setSeconds(arr.getSeconds + schedule.duration);

                let numTickets = data.passengers_number;
                let amount = numTickets * schedule.price;

               let booking = bookingUtils.createBooking(scheduleId, departure_xport, arrival_xport, departure_date, arrival_date, numTickets, amount);

                resolve(booking);
            }catch(error){
                reject (error);
            }
        });
    }



}
