const userUtils = require('../Utils/userUtils');
const userAuthorityUtils = require('../Utils/userAuthorityUtils');

module.exports = {

    getUserById: function(id) {
        return userUtils.getUserById(id);
    },

    getUserByEmail: function(email) {
        return userUtils.getUserByEmail(email);
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
        //console.log("TYPE", type)
        if(password===confirmPassword) {
            if(type==="1") {
                await userUtils.addUser(name, surname, email, password).then(async () => {
                    //console.log("ID_USER", id_user)
                    let user = await userUtils.getUserByEmail(email);
                    console.log("user add" , user)
                    return userUtils.insertAuthority(1, user.id)})
                
            } else {
                let id_admin = userUtils.addAdmin(name, surname, email, password);
                return userUtils.insertAuthority(2, id_admin)
            }
             
        }else {
            console.log("password non coincidono")
        }
       
    }

}