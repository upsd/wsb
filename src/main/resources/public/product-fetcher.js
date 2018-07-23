window.onload = () => {
    document.getElementById('product-retriever').addEventListener("click", () => {
        fetch('http://localhost:4567/products')
        .then(res => res.json())
        .then(data => data.products.forEach(product => {
            const productListing = document.createElement('div');
            productListing.innerHTML = `<p>${product.title} - &pound;${product.price}</p>`;
            document.body.appendChild(productListing);
        }));
    });
};