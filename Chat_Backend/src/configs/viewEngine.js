const path = require('path')
const express = require('express')
var morgan = require('morgan')



const conFigViewEngiine = (app , ) => {
// public views
    app.set('views', path.join('./src', 'views'));
    app.set('view engine','ejs')
//static file
    // app.use(express.static(path.join('./src', 'public')))

// sử dụng .env    
    require('dotenv').config()

    app.use(morgan('combined'))

    

}

module.exports = conFigViewEngiine