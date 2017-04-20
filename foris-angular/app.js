
/**
 * Module dependencies.
 */

var express = require('express')
    , routes = require('./routes')
  , user = require('./routes/user')
  , http = require('http')
  , sensorTopology = require('./routes/sensortopology')
  , weather = require('./routes/weather')
  , path = require('path');

var app = express()

app.use(express.cookieParser());
app.use(express.session({secret:'forissession',duration:30*60*1000}));

// all environments
app.set('port', process.env.PORT || 3120);
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}

//Common Functions
app.get('/', routes.index);
app.get('/users', user.list);
app.get('/login',user.login);
app.get('/checklogin', user.checklogin);
app.post('/signin',user.signin);
app.post('/signup',user.signup);
app.get('/profileusername', user.profileusername);
app.get('/logoutsession',user.logoutsession);
app.get("/sensordata",user.sensordata);

//Sensor topology
app.get("/sensortopology", user.sensortopology);
/*app.get("/sensortopology", user.sensortopology);
app.get("/sensortopology/map", user.map);
app.get("/sensortopology/realTimeData", user.realTimeData);*/
//app.get('/sensorTopology', sensorTopology.renderTopology);
app.get('/sensorRealTimeData', sensorTopology.sensorRealTimeData);
app.get('/sensorInfo', sensorTopology.sensorInfo);
//end of sensor topology

//pages
app.get('/home',user.home);
app.get('/humidity',user.humidity);
app.get('/water',user.water);
app.get('/temperature',user.temperature);
app.get('/moisture',user.moisture);
app.get('/pH',user.pH);
app.get('/salinity',user.salinity);
app.get('/hometest', routes.hometest);
app.get('/weatherdata', weather.fetchWeatherData);
app.get('/weatherdatamin', weather.fetchWeatherDataMin);


http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});
