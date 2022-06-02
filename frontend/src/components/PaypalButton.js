import './PaypalButton.css';
import axios from "axios";
import { useNavigate } from "react-router-dom";
import PaypalExpressBtn from 'react-paypal-express-checkout';
import { useContext } from 'react';
import cartContext from '../context/CartContext';

const PaypalButton = (props) => {
    const { cartTotal, clearCart } = useContext(cartContext)
    const baseURL = process.env.REACT_APP_API_URL || "";
    const finalCost = cartTotal.toFixed(2);
    const navigate = useNavigate();
    const onSuccess = (payment) => {
        clearCart();
        console.log(props.tickets)
        axios.post(`${baseURL}/api/confirmpurchase`, {
            paymentId: payment['paymentID'],
            paymentDetails: JSON.stringify(payment),
            tickets: JSON.stringify(props.tickets),
            // tickets: [{
            //     moviedbId: "ooga",
            //     units: 2,
            //     showTime: "12:00"
            // }
            // ]
        })
        // window.location.assign(`${baseURL}/api/confirmpurchase?payment=${payment['paymentID']}&show=${props['id']}`);
        navigate(`/checkout`);
    }

    const onCancel = (data) => {
        // User pressed "cancel" or close Paypal's popup!
        console.log('The payment was cancelled!', data);
        // You can bind the "data" object's value to your state or props or whatever here, please see below for sample returned data
    }

    const onError = (err) => {
        // The main Paypal's script cannot be loaded or somethings block the loading of that script!
        console.log("Error!", err);
        // Because the Paypal's main script is loaded asynchronously from "https://www.paypalobjects.com/api/checkout.js"
        // => sometimes it may take about 0.5 second for everything to get set, or for the button to appear
    }

    let env = 'sandbox'; // you can set here to 'production' for production
    let currency = 'USD'; // or you can set this value from your props or state
    let total = `${finalCost}`; // same as above, this is the total amount (based on currency) to be paid by using Paypal express checkout
    // Document on Paypal's currency code: https://developer.paypal.com/docs/classic/api/currency_codes/

    const client = {
        sandbox: 'AWJjvRIBZ2yPqhVm8LNKb5tzgxFQMa92wCdPBMD311UZyIQyZyMvTvmY031JPUjB5JyEk4Wv1-_Ngp2g'
    }
    // In order to get production's app-ID, you will have to send your app to Paypal for approval first
    // For sandbox app-ID (after logging into your developer account, please locate the "REST API apps" section, click "Create App"):
    //   => https://developer.paypal.com/docs/classic/lifecycle/sb_credentials/
    // For production app-ID:
    //   => https://developer.paypal.com/docs/classic/lifecycle/goingLive/

    // NB. You can also have many Paypal express checkout buttons on page, just pass in the correct amount and they will work!
    return (
        <PaypalExpressBtn env={env} client={client} currency={currency} total={total} onError={onError} onSuccess={onSuccess} onCancel={onCancel} />
    );
}

export default PaypalButton;