
module.exports = {
    parseDate: function (date_input) {
            
        if (date_input == null) {
            return undefined;
        }
        let date = new Date(date_input);
        let finalDate = '';

        let day = String(date.getDate()).padStart(2, '0');
        let month = String(date.getMonth() + 1).padStart(2, '0');
        let year = date.getFullYear();
       
        /* let hours = String(date.getHours()).padStart(2, '0');
        let minutes = String(date.getMinutes()).padStart(2, '0');
        let seconds = String(date.getSeconds()).padStart(2, '0');
        */
        
        finalDate = day + '/' + month + '/' + year;

        return finalDate;
    }
}