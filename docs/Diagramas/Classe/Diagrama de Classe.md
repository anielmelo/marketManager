## 1. Diagrama de Classe Estoque

```plantuml-svg

class Fornecedor { 
	- id: Long
	- nome: String   
	- cnpj: String   
	- endereco: String 
}  

class Produto {   
	- id: Long
	- id_fornecedor: Long
	- nome: String   
	- marca: String   
	- custo: double 
}  

class Lote {  
	- id: Long
	- id_produto: Long   
	- id_recebimento: Long   
	- quantidade_disponivel: int   
	- quantidade_total: int 
}  

class Recebimento {   
	- id: Long
	- data: Timestamp 
}  

Fornecedor "1" --> "0..*" Produto : fornece 
Produto "1" --> "0..*" Lote : pertence 
Recebimento "1" --> "0..*" Lote : contem  

```

## 2. Diagrama de Classe Venda

```plantuml-svg

class Venda {   
	- id: Long
	- data: Date 
}

class VendaProduto {   
	- id: Long
	- id_venda: Long   
	- id_produto: Long   
	- quantidade: int   
	- valor: double   
	- total: double 
}

note top of VendaProduto : id_produto referencia um id de um produto de estoque

Venda "1" --> "0..*" VendaProduto : possui 

```

## 3. Diagrama de Classe Autenticação

```plantuml-svg

class Usuario {   
	- id: Long
	- nome: String   
	- senha: String   
	- cargo: String 
}

```