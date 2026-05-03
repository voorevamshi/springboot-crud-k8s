### Port Conflict(Application:8000 Vs Kubernetes:8080)
That error is actually helpful! It tells us exactly where the "disconnect" is.
$ kubectl port-forward svc/springboot-crud-svc 9090:8000
error: Service springboot-crud-svc does not have a service port 8000

Kubernetes is being strict: It says, "You're asking me to forward to port 8000, but in my Service definition (`springboot-crud-svc`), I don't see any port 8000 listed."

### The Conflict

-   **Your Spring Boot App:** Is listening on **8000** (from your logs).
    
-   **Your K8s Service:** Is only configured to recognize port **8080** (based on your earlier YAML).
    

### The Solution

When you port-forward, the command follows this pattern: `local-port : service-port`. Since your service only knows about 8080, you must use 8080 in the command, even if the app inside is on 8000.

**However**, because your Service's `targetPort` is likely still 8080, it's sending traffic to a dead end inside the pod.

#### Step 1: Fix the Service "On the Fly"

Run this command to tell the service to send traffic to the correct port (8000) inside the pod:

Bash

```
kubectl patch svc springboot-crud-svc -p '{"spec":{"ports":[{"port":8080,"targetPort":8000}]}}'

```

#### Step 2: Run the Port-Forward

Now that the Service knows to map 8080 to 8000, run this:

Bash

```
kubectl port-forward svc/springboot-crud-svc 9090:8080

```

#### Step 3: Test in Postman

Use this URL: `http://localhost:9090/orders`

----------

### Why this is the "final piece" of the puzzle:

In your `application.yaml`, you set `server.port: 8000`. In Kubernetes, a Service acts like a receptionist.

1.  You talk to the **Service** on port **8080**.
    
2.  The Service looks at its notes and says, "Okay, I'll pass this to the **Pod**."
    
3.  If the Service's notes (`targetPort`) say **8080**, but the Pod is listening on **8000**, the Pod refuses the connection.
    

### Permanent fix for your next build:

In your `app-deployment.yaml`, ensure your Service looks exactly like this so you don't have to run "patch" commands again:

YAML

```
spec:
  ports:
    - protocol: TCP
      port: 8080      # The port you use for port-forwarding
      targetPort: 8000 # This MUST match server.port: 8000 in your code

```

Give that `patch` and `port-forward` combo a shot. If the terminal says `Handling connection`, your Postman request should finally return that Nokia order!
