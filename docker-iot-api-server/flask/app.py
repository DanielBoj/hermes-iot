from flask import Flask, render_template
from domain.mongo_sensors_api import api_blueprint


def create_app():
    app = Flask(__name__, template_folder='./presentation/templates')
    app.register_blueprint(api_blueprint)

    @app.route('/')
    def index():
        return render_template('index.html')

    return app


if __name__ == '__main__':
    app = create_app()
