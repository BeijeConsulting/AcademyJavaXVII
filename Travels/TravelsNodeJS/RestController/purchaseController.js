const purchaseUtils = require('../Utils/purchaseUtils');
const userUtils = require('../Utils/userUtils');


module.exports = {
    getAllPurchases: function(){
        return new Promise(async (resolve, reject) =>{
            try{
                let purchases = await purchaseUtils.getAllPurchases();
                const purchasePromises = purchases.map(async (purchase) =>{
                    let user = await userUtils.getUserById(purchase.user_id);
                    purchase.user = user;
                    //console.log("purhase: ", purchase);

                });
                await Promise.all(purchasePromises);
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