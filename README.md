Store-POS
🚀 Project Overview
💡 Introduction
This is a Point-of-Sale (POS) system designed for small retail businesses. It allows users to manage products, process sales, apply discounts, and generate reports.

📦 Technologies Used
List the main technologies and tools used in the project.

Java

Swing (for the GUI)

SQLite (for database management)

Maven (for project management)

Git/GitHub (for version control)

⚙️ Features
✔️ Core Features
List the core features your POS system has.

Add Products to Stock: Admins can add new products to the inventory.

Sale Processing: Clerks can add products to the cart, apply discounts, and finalize sales.

Stock Management: Automatically updates stock after a sale.

Reporting: Sales reports can be generated and exported.

🛠️ Additional Features 

Discount System: Offers cash or percentage discounts.

Barcode Scanning: Allows adding products via barcode scanning.

Receipt Printing: It shows the receipt (Might allow to export to pdf later)


🏗️ Setup Instructions
💻 Installation
Step 1: Clone the repository.

git clone [<repository-url>](https://github.com/MoeRon2/store-pos.git)

Step 2: Install dependencies.

mvn install


🧑‍💻 Running the Application

> Will add .jar app when close to finishing


Step 1: Compile and run the project.

If using Maven:

mvn exec:java
🖥️ User Guide
🏷️ Adding Products
Navigate to the "Stock" menu.

Select "Add Product" to open the form.

Enter product details (name, price, stock, etc.).

Click Save to add the product to the inventory.

🛒 Adding Items to Cart
Use the barcode scanner or type the product barcode in the barcode field.

The product will automatically be added to the cart if available in stock.

Adjust quantities using the "+" and "-" buttons.

💳 Completing a Sale
Once the items are added to the cart, click on Finish Sale.

Select the payment method (Cash/Card).

Confirm the transaction to generate a receipt.

🛠️ Admin Guide
📊 Managing Stock
Viewing Stock: Navigate to the "Stock" menu and select "View Stock" to see all available products.

Updating Products: Use the "Update Product" option to modify existing product details (e.g., price, stock quantity).

📈 Exporting Data
Export Stock: Export the stock list to Excel for reporting purposes.

Export Sales: Export completed sales records to Excel.

⚠️ Error Handling
🛑 Common Errors
Out of Stock: If the product is out of stock, an error message will appear when trying to add it to the cart.

Invalid Barcode: If an invalid barcode is entered, the system will display an error message.

Discount Error: If an invalid discount value is entered, the system will show an error.



📂 Project Structure
/src: Contains the main source code.

✅ TODO
Polish UI/UX: Focus on improving the user interface.

Add Logging: Implement logging to monitor system events.

Testing: Write unit tests and integration tests.

📅 Progress Updates




