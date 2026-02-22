# E-Commerce Management System

A Java console application for managing e-commerce operations, including products, customers, orders, and reviews — built with custom data structures (BST, LinkedList, ArrayList).

## Prerequisites

* Java JDK 8+

> No external libraries required. All data structures are implemented from scratch.

## Setup Steps

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/ecommerce-management-system.git
cd ecommerce-management-system
```

### 2. Prepare the dataset

Place your CSV files in a `dataset/` folder at the project root:

```
dataset/
├── products.csv
├── customers.csv
├── reviews.csv
└── orders.csv
```

### 3. Compile and run

```bash
javac *.java
java main
```

## Usage

### Login Options

On launch, select your role:

* **Admin Login** — ID: `1`, Name: `admin`
* **Customer Login** — Enter your customer ID

### Admin Operations

| Category | Operations |
|---|---|
| Products | View, Add, Update Price/Stock, Remove |
| Customers | View (alphabetically), Register, View Orders |
| Orders | View all, Filter by date range |
| Reviews | View all, View by product/customer, Remove |
| Analytics | Top 3 by rating, Price range query, Customers who reviewed a product |

### Customer Operations

| Category | Operations |
|---|---|
| Orders | Create, View, Add/Remove product, Cancel |
| Reviews | Write, Edit, Remove, View mine, View by product |
| Products | Browse all products |

## Features

* ✅ Admin and Customer login system
* ✅ Full CRUD for products, orders, and reviews
* ✅ CSV data loading on startup
* ✅ BST-based fast search for products, customers, and orders
* ✅ Date range filtering for orders
* ✅ Price range filtering for products
* ✅ Top 3 products by average rating
* ✅ Alphabetical customer listing
* ✅ Stock management (auto-decrement/increment on order add/remove)

## Project Structure

```
ecommerce-management-system/
├── main.java               # Entry point & menus
├── ECommerceSystem.java    # Core business logic
├── Customer.java
├── Product.java
├── Order.java
├── Review.java
├── BST.java                # Binary Search Tree
├── BSTNode.java
├── LinkedList.java
├── ArrayList.java
├── Node.java
├── BooleanWrapper.java
├── CSVReader.java          # CSV data loader
└── dataset/
    ├── products.csv
    ├── customers.csv
    ├── reviews.csv
    └── orders.csv
```

## Tech Stack

**Java** • **Custom BST** • **Custom LinkedList** • **Custom ArrayList** • **CSV I/O**
