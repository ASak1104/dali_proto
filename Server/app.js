const express = require('express');
const logger = require('morgan');
const dotenv = require('dotenv');
const path = require('path');
const cookieParser = require('cookie-parser');
const session = require('express-session');
const passport = require('passport');

dotenv.config()
const { connect } = require('./schemas');
const indexRouter = require('./routes/index');
const authRouter = require('./routes/auth');
const profileRouter = require('./routes/profile');
const mapRouter = require('./routes/map');
const adminRouter = require('./routes/admin');
const passportConfig = require('./passport');

const app = express();
passportConfig();
app.use(session({
    resave: false,
    saveUninitialized: false,
    secret: process.env.COOKIE_SECRET,
    cookie: {
        httpOnly: true,
        secure: false,
    },
}));
app.use(passport.initialize());
app.use(passport.session());

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
// app.use(cookieParser());
connect()

app.use('/', indexRouter);
app.use('/auth', authRouter);
app.use('/profile', profileRouter);
app.use('/map', mapRouter);
app.use(process.env.ADMIN_ROOT, adminRouter);

app.use((req, res, next) => {
    const error =  new Error(`Not exist ${req.method} ${req.url} router`);
    error.status = 404;
    next(error);
});

app.use((err, req, res, next) => {
    console.log(err);
    res.locals.message = err.message;
    res.locals.error = process.env.NODE_ENV !== 'production' ? err : {};
    res.status(err.status || 500).send();
});

app.listen(app.get('port'), () => {
    console.log(`Listening localhost:${app.get('port')}`);
});


module.exports = app;
