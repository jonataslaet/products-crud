import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductDTO } from '../models/product.dto';

const baseURL = 'http://localhost:8080/api/products';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  readAll(): Observable<ProductDTO[]> {
    return this.httpClient.get<ProductDTO[]>(baseURL);
  }

  read(id): Observable<ProductDTO> {
    return this.httpClient.get<ProductDTO>(`${baseURL}/${id}`);
  }

  create(data): Observable<ProductDTO> {
    return this.httpClient.post<ProductDTO>(baseURL, data);
  }

  update(id, data): Observable<ProductDTO> {
    return this.httpClient.put<ProductDTO>(`${baseURL}/${id}`, data);
  }

  delete(id): Observable<void> {
    return this.httpClient.delete<void>(`${baseURL}/${id}`);
  }

  deleteAll(): Observable<void> {
    return this.httpClient.delete<void>(baseURL);
  }

  searchByName(name): Observable<ProductDTO[]> {
    return this.httpClient.get<ProductDTO[]>(`${baseURL}?name=${name}`);
  }
}
