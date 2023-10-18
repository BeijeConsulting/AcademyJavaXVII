const userUtils = require('../Utils/userUtils');
const userAuthorityUtils = require('../Utils/userAuthorityUtils');

module.exports = {

    getUserById: function(id) {
        return userUtils.getUserById(id);
    },

    getUserByEmail: function(email) {
        return userUtils.getUserByEmail(email);
    },

    getUserByEmailAndPassword: async function(data) {
        return new Promise(async (resolve, reject) => {
            let email = data.email;
            let password = data.password;
            
            let p = await userUtils.getUserByEmailAndPassword(email, password);
            console.log("permission: ", p);
            let permission;
            if (p.count  === 1){
                permission = ["USER"];
            } else if (p.count === 2) {
                permission = ["USER", "ADMIN"];
            } else reject("Login failed");

            let map = { 
                id : p.id,
                email : email,
                //token : "ciao",
                permission : permission
            }

            resolve(map);
        });
    },


    getAllAdmin: function() {
        return userAuthorityUtils.getAllAdmins();
    },

    getAllCustomers: function() {
        return userAuthorityUtils.getAllCustomers();
    },

    getUserById: function(id){
        return userUtils.getUserById(id);
    },

    editUserDetails: function(id,name,surname){
        return userUtils.editUserDetails(id,name,surname)
    },

    editUserPassword: function(id, oldPw, newPw){ 
        return userUtils.editUserPassword(id,oldPw,newPw)
    },

    disableUser: function(id){
        return userUtils.disableUser(id);
    },

    disableUserByEmail: function(email){
        return userUtils.disableUserbyEmail(email);
    },

    addUser: async function(name, surname, email, password, confirmPassword, type){
        return new Promise(async (resolve, reject) => {
            if(password===confirmPassword) {
                if(type==="1") {
                    await userUtils.addUser(name, surname, email, password).then(async () => {
                        //console.log("ID_USER", id_user)
                        let user = await userUtils.getUserByEmail(email);
                        console.log("user add" , user)
                        await userUtils.insertAuthority(1, user.id)})
                    
                } else {
                    await userUtils.addAdmin(name, surname, email, password).then(async () => {
                        //console.log("ID_USER", id_user)
                        let user = await userUtils.getUserByEmail(email);
                        console.log("admin add" , user);
                        await userUtils.insertAuthority(2, user.id);
                        await userUtils.insertAuthority(1, user.id);
                    });
                }
                resolve(true);
            }else {
                reject("Passwords don't match");
            }
        })
       
    },

    signup: async function(data){
        return new Promise(async (resolve, reject) => {

            let name = data.name;
            let surname = data.surname;
            let email = data.email;
            let password = data.password;
            let confirmPassword = data.confirmPassword;
            let type = "1";
            try{
                let result = await this.addUser(name, surname, email, password, confirmPassword, type);
                resolve(result);
            } catch(error){
                reject(error);
            }
            
        });
    } 

}