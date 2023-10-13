const purchaseUtils = require('../Utils/purchaseUtils');
const userUtils = require('../Utils/userUtils');


module.exports = {
    getAllPurchases: function(){

        return purchaseUtils.getAllPurchases();
    },

    getPurchasesByUserId: function(user_id){
        return purchaseUtils.getPurchaseByUserId(user_id);
    }

}