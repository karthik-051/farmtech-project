# Import necessary libraries
from flask import Flask, render_template, request, redirect, url_for
import cv2
import numpy as np
from tensorflow.keras.applications.inception_v3 import InceptionV3
from tensorflow.keras.applications.inception_v3 import preprocess_input, decode_predictions

# Create a Flask web app
app = Flask(__name__)

# Load the pre-trained InceptionV3 model
model = InceptionV3(weights='imagenet')

# Function to process and predict the image
def process_and_predict(image_path):
    # Read and preprocess the image
    img = cv2.imread(image_path)
    img = cv2.resize(img, (299, 299))
    img = np.expand_dims(img, axis=0)
    img = preprocess_input(img)

    # Make a prediction
    predictions = model.predict(img)
    decoded_predictions = decode_predictions(predictions)

    # Get the top prediction
    top_prediction = decoded_predictions[0][0][1]

    return top_prediction

# Route for the main page
@app.route("/", methods=["GET", "POST"])
def index():
    if request.method == "POST":
        # Check if the post request has the file part
        if 'file' not in request.files:
            return redirect(request.url)
        
        file = request.files['file']

        # If the user does not select a file, return to the main page
        if file.filename == '':
            return redirect(request.url)

        # Save the uploaded file
        file.save("uploaded_image.jpg")

        # Process and predict the image
        prediction = process_and_predict("uploaded_image.jpg")

        # Display the result
        return render_template("result.html", prediction=prediction)

    # Render the main page
    return render_template("index.html")

# Run the app
if __name__ == "__main__":
    app.run(debug=True)
