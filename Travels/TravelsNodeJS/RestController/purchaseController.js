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
        let purchase_id = await purchaseUtils.addPurchase(user_id, total, nTickets);
        let name;
        let surname;
        for (let i = 0; i < passengers.length; i++){
            name = passengers[i].name;
            surname = passengers[i].surname;
            await passengerUtils.addPassenger(purchase_id, name, surname)
        }
        let departureXportId;
        let departure_date;
        let arrivalXportId;
        let arrival_date;

        for (let i = 0; i < bookings.length; i++){
            departureXportId = bookings[i].departureXport.id;
            departure_date = bookings[i].departure_date.split(".")[0].replace("T", " ");
            arrivalXportId = bookings[i].arrivalXport.id;
            arrival_date = bookings[i].arrival_date.split(".")[0].replace("T", " ");

            let travel = await travelUtils.getTravelByScheduleAndDate(bookings[i].scheduleId, departure_date.split(" ")[0]);
            let travel_id;

            if (travel == null || travel == undefined){
                travel_id = await travelUtils.addTravel().insertedId;
            } else{
                travel_id = travel.id;
            }

            await bookingUtils.addBooking();

        }
        //prendere da schedule id la schedule e caricare route id e seats
        //modificare in else il numero di posti vuoti
        
    
    }

}