const containerPay = document.getElementById('amountPay');

const form = document.getElementsByTagName('form')[0];

const selectCategory = document.getElementById('categories');
const inputQuantity = document.getElementById('quantity');

const discount = (initialPrice) => {
  let price = 0;
  let quantity = inputQuantity.value;

  switch (selectCategory.value) {
    case '1': // Estudiante
      price = initialPrice * quantity * 0.2;
      break;
    case '2': // Trainee
      price = initialPrice * quantity * 0.5;
      break;
    case '3': // Junior
      price = initialPrice * quantity * 0.85;
      break;
    default:
      break;
  }

  return price;
};

const resume = (e) => {
  e.preventDefault();
  const totalPrice = discount(200);
  containerPay.innerHTML = totalPrice;
};

form.addEventListener('submit', resume);
form.addEventListener('reset', () => {
  containerPay.innerHTML = '';
});
