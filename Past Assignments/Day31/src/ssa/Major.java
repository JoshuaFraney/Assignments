package ssa;

import javax.persistence.*;

	@Entity
	@Table(name="major")
	
	public class Major {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		int id;
		
		@Column(name="description")
		String description;
		
		@Column(name="req_sat")
		int req_sat;
		
		public Major() {}
		
		public Major(String description, int req_sat) {
			super();
			this.description = description;
			this.req_sat = req_sat;
}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getReq_sat() {
			return req_sat;
		}

		public void setReq_sat(int req_sat) {
			this.req_sat = req_sat;
		}
		
		@Override
		public String toString() {
			String msg = (String.format("%2d %-16s %8d", getId(), getDescription(), getReq_sat()));
			return msg;
			}
		
		}
