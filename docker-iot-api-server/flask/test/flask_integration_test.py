import unittest
from main import app


class FlaskIntegrationTest(unittest.TestCase):

    def setUp(self) -> None:
        app.config['TESTING'] = True
        self.client = app.test_client()

    def test_get_sensor(self):
        response = self.client.get('/api/sensors')
        self.assertEqual(response.status_code, 200)
        self.assertEqual(response.content_type, 'application/json')
