import pandas as pd
import psycopg2

# Configurações de conexão ao PostgreSQL
db_config = {
    "host": "localhost",
    "port": "5432",
    "database": "esigProject",
    "user": "postgres",
    "password": "123456789",
}

# Caminho para o arquivo .xlsx
xlsx_file_path = "./dbFile.xlsx"

# Conexão com o banco de dados PostgreSQL
conn = psycopg2.connect(**db_config)
cursor = conn.cursor()

# Leitura de todas as planilhas do arquivo Excel
xls = pd.ExcelFile(xlsx_file_path)
# Itera sobre cada planilha e insere os dados na tabela correspondente 
sheet_names = ["Cargo", "Vencimentos", "Cargo_Vencimentos","Pessoa"]
for sheet_name in sheet_names:
    # Lê a planilha atual em um DataFrame
    df = pd.read_excel(xls, sheet_name=sheet_name)
    
    if 'Data_Nascimento' in df.columns:
        df['Data_Nascimento']= pd.to_datetime(df['Data_Nascimento'], format='%m/%d/%Y')
    # Define o nome da tabela com base no nome da planilha
    table_name = sheet_name
    
    

    
    # Inserção dos dados na tabela correspondente
    i=0
    for index, row in df.iterrows():
        try:
            if table_name != "Pessoa" or not pd.isna(row['Cargo_ID']):
                # Constrói a consulta SQL para inserção na tabela atual
                insert_query = f"INSERT INTO {table_name} ({', '.join(df.columns)}) VALUES ({', '.join(['%s'] * len(df.columns))})"
               
                cursor.execute(insert_query, tuple(row))
                conn.commit()
                i +=1
        except psycopg2.IntegrityError as e:
            conn.rollback()  # Desfaz a transação em caso de exceção 
            print(f"Erro de chave única: {e}")
            i +=1

cursor.close()
conn.close()