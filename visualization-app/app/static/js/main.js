const svgns = "http://www.w3.org/2000/svg";
const ws_movement_endpoint = 'ws://localhost:8003/ws/movement';
const ws_coupons_endpoint = 'ws://localhost:8003/ws/coupons';
const new_scenario_endpoint = '/api/new_scenario';
const customers_endpoint = '/api/customers';

const customer_color = '#FAF5D8';
const observed_customer_color = '#CC8748';

const ENTER_STR = 'ENTER'
const MOVEMENT_STR = 'MOVE';
const FOCUS_STR = 'FOCUS';
const EXIT_STR = 'EXIT';

const ENTER_POINT = { x: 937, y: 50 };
const EXIT_POINT = { x: 1071, y: 50 };

const svg_preview = document.getElementById("store-plan-preview");
const svg_scenario = document.getElementById("store-plan-scenario");
const customer_select_simulator = $('#customer-selector-simulator');
const customer_select_scenario = $('#customer-selector-scenario');
const customer_speed_slider = $('#customer-speed-slider');

const view_box_x = 1808;
const view_box_y = 1315;

var observed_customer = 1;
var customers = [];
var scenario = [];
var customer_data = [];


$(document).ready(function () {
    customers_details();
    observe_customers();
    simulate();
    create_scenario();
    handle_speed_slider();
});
