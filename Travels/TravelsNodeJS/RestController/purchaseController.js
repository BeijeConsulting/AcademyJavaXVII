const purchaseUtils = require('../Utils/purchaseUtils');
const userUtils = require('../Utils/userUtils');


module.exports = {
    getAllPurchases: function(){

        return new Promise(async (resolve, reject) =>{
            try{
                let purchases = await purchaseUtils.getAllPurchases();
                const purchasePromise = purchases.map(async (purchase) =>{
                    let user = await userUtils.getUserById(purchases.user_id);
                    purchase.user = user;

                });
                await Promise.all(purchasePromise);
                resolve(purchases);

            } catch (error) {
                reject(error);
            }

        });
    },

    getPurchasesByUserId: function(user_id){
        return purchaseUtils.getPurchaseByUserId(user_id);
    }

}