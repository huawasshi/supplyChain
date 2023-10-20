package main

import (
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	"bytes"
	"time"
	pb "github.com/hyperledger/fabric/protos/peer"
)

type basicInfo struct{
	traceability_id     string  `json:"traceability_id"`
	name                string  `json:"name"`
	trace_date          string  `json:"trace_date"`
	delivery_date       string  `json:"delivery_date"`
	trace_number        string  `json:"trace_number"`
	out_company         string  `json:"out_company"`
	out_tel             string  `json:"out_tel"`
	out_person          string  `json:"out_person"`
	out_area            string  `json:"out_area"`
	in_company          string  `json:"in_company"`
	in_tel              string  `json:"in_tel"`
	in_person           string  `json:"in_person"`
	in_area             string  `json:"in_area"`
}



func (t *basicInfo) Init (stub shim.ChaincodeStubInterface) pb.Response{
	return shim.Success(nil)
}

func (t *basicInfo) Invoke(stub shim.ChaincodeStubInterface) pb.Response{
	funcName,args := stub.GetFunctionAndParameters()
	if(funcName=="save"){
		return t.saveBasic(stub,args)
	}else if(funcName=="query"){
		return t.queryBasic(stub,args)
	}else if(funcName=="queryHistory"){
		return t.queryHistory(stub,args)
	}else{
		return shim.Error("no such function")
	}
}

func (t *basicInfo) saveBasic(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	if(len(args)!=2){
		return shim.Error("except two args")
	}else{
		err:=stub.PutState(args[0],[]byte(args[1]))
		if(err!=nil) {
			return shim.Error(err.Error())
		}
		return shim.Success(nil)
	}

}

func (t *basicInfo) queryBasic(stub shim.ChaincodeStubInterface, args []string) pb.Response{

	if(len(args)!=1){
		return shim.Error("except one arg")
	}else{
	  value,err :=stub.GetState(args[0])
	  if(err!=nil){
	  	shim.Error("no data found")
	  }
	  return shim.Success(value)
	}
}

func (t *basicInfo) queryHistory(stub shim.ChaincodeStubInterface, args []string) pb.Response{

	marblename := args[0]

	resultIterator, err := stub.GetHistoryForKey(marblename)

	if err != nil {
		return shim.Error(err.Error())
	}

	defer resultIterator.Close()

	var buffer bytes.Buffer

	buffer.WriteString("[")

	isWrite := false
	for resultIterator.HasNext() {
		queryResponse , err := resultIterator.Next()

		if err != nil {
			return shim.Error(err.Error())
		}

		if isWrite == true {
			buffer.WriteString(",")
		}

		buffer.WriteString("{ \"TxId\":\"")
	
		buffer.WriteString(queryResponse.TxId)



		buffer.WriteString("\",\"Timestamp\": \"")
		buffer.WriteString(time.Unix(queryResponse.Timestamp.Seconds , int64(queryResponse.Timestamp.Nanos)).String())


		buffer.WriteString("\",\"Value\": ")
		buffer.WriteString(string(queryResponse.Value))

		buffer.WriteString("}")

		isWrite = true
	}

	buffer.WriteString("]")

	return shim.Success(buffer.Bytes())
}




func main(){
	err:=shim.Start(new(basicInfo))
	if(err!=nil){
		fmt.Println("emr basicInfo chaincode start error")
	}
}