import axios, { AxiosError } from 'axios';
import { Problem } from '../model/Problem.model';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Content-Type': 'application/json',
  },
});

apiClient.interceptors.response.use(
  (response) => response, 
  (error: AxiosError) => {
    if (error.response && error.response.data) {
      const problem : Problem = error.response.data as Problem;      
        
      return Promise.reject(problem);
    }

    return Promise.reject(new Error('Erro desconhecido ao se comunicar com o servidor.'));
  }
);

export default apiClient;