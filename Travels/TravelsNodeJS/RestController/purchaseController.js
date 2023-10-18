const bookingUtils = require('../Utils/bookingUtils');
const passengerUtils = require('../Utils/passengerUtils');
const purchaseUtils = require('../Utils/purchaseUtils');
const travelUtils = require('../Utils/travelUtils');
const userUtils = require('../Utils/userUtils');
const xportUtils = require('../Utils/xportUtils');


module.exports = {
    getAllPurchases: function(){

        return purchaseUtils.getAllPurchases();
    },

    getPurchasesByUserId: function(user_id){
        return purchaseUtils.getPurchaseByUserId(user_id);
    },
    
    addPurchase: async function(user_id, data){
        let passengers = data.passengers;
        console.log("passengers : ", passengers)
		let bookings = data.bookings;
		let total =  data.total;
		let nTickets = data.nTickets;
        let purchase_id = await purchaseUtils.addPurchase(user_id, total, nTickets * passengers.length);
        let name;
        let surname;
        for (let i = 0; i < passengers.length; i++){
            name = passengers[i].name;
            surname = passengers[i].surname;
            await passengerUtils.addPassenger(purchase_id, name, surname)
        }
        let scheduleId;
        let routeId;
        let seats;
        let departureXportId;
        let departure_date;
        let arrivalXportId;
        let arrival_date;
        let travel;
        let travel_id;
        let booking_price;

        for (let i = 0; i < bookings.length; i++){
            scheduleId = bookings[i].scheduleId;
            routeId = bookings[i].routeId;
            seats = bookings[i].seats;
            departureXportId = bookings[i].departureXport.id;
            departure_date = bookings[i].departure_date.split(".")[0].replace("T", " ");
            arrivalXportId = bookings[i].arrivalXport.id;
            arrival_date = bookings[i].arrival_date.split(".")[0].replace("T", " ");

            travel = await travelUtils.getTravelByScheduleAndDate(scheduleId, departure_date.split(" ")[0]);

            if (travel == null || travel == undefined){
                travel_id = await travelUtils.addTravel(scheduleId, routeId, departure_date.split(" ")[0], arrival_date.split(" ")[0], seats - nTickets);
            } else{
                travel_id = travel.id;
                let current_seats = travel.empty_seats;
                await travelUtils.editEmptySeats(travel_id, current_seats - nTickets);
            }

            booking_price = bookings[i].amount;

            await bookingUtils.addBooking(purchase_id, departure_date, arrival_date, departureXportId, arrivalXportId, nTickets, booking_price, travel_id, scheduleId);

        }
    
    }

}